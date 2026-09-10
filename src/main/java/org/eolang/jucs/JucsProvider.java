/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2026 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.jupiter.params.support.ParameterDeclarations;

/**
 * Provider of the sources.
 * @since 0.0.1
 */
final class JucsProvider implements ArgumentsProvider,
    AnnotationConsumer<ClasspathSource> {

    /**
     * Line separator used to split classpath listings.
     */
    private static final Pattern NEWLINE = Pattern.compile("\\R");

    /**
     * The annotation of the user.
     */
    private ClasspathSource annotation;

    @Override
    public void accept(final ClasspathSource ant) {
        this.annotation = ant;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(
        final ParameterDeclarations params, final ExtensionContext ctx) {
        return this.yamls("", params.getAll().size() > 1).stream();
    }

    private Collection<Arguments> yamls(final String prefix, final boolean withpath) {
        final Collection<Arguments> out = new ArrayList<>(0);
        final String home = String.format("%s/%s", this.sanitized(), prefix);
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(
            String.format("glob:%s", this.annotation.glob())
        );
        final String[] subs = JucsProvider.NEWLINE.split(
            new UncheckedText(new TextOf(new ResourceOf(home))).asString(), -1
        );
        for (final String sub : subs) {
            if (sub.isEmpty()) {
                continue;
            }
            final Path path = Paths.get(String.format("%s%s", prefix, sub));
            if (matcher.matches(path)) {
                final String content = new UncheckedText(
                    new TextOf(new ResourceOf(String.format("%s%s", home, sub)))
                ).asString();
                final String normalized = JucsProvider.normalize(path);
                if (withpath) {
                    out.add(
                        Arguments.of(Named.of(normalized, content), normalized)
                    );
                } else {
                    out.add(Arguments.of(Named.of(normalized, content)));
                }
            } else if (JucsProvider.directory(String.format("%s%s", home, sub))) {
                out.addAll(this.yamls(String.format("%s%s/", prefix, sub), withpath));
            }
        }
        return out;
    }

    private static boolean directory(final String resource) {
        boolean directory = false;
        final URL url = JucsProvider.class.getClassLoader().getResource(
            resource.concat("/")
        );
        if (url != null) {
            try {
                if ("file".equals(url.getProtocol())) {
                    directory = Files.isDirectory(Paths.get(url.toURI()));
                } else if ("jar".equals(url.getProtocol())) {
                    final JarURLConnection connection =
                        (JarURLConnection) url.openConnection();
                    directory = connection.getJarEntry() != null
                        && connection.getJarEntry().isDirectory();
                }
            } catch (final IOException | URISyntaxException err) {
                throw new IllegalStateException(
                    String.format("Can't inspect classpath resource '%s'", resource), err
                );
            }
        }
        return directory;
    }

    private static String normalize(final Path path) {
        return path.toString().replace('\\', '/');
    }

    private String sanitized() {
        final String path = this.annotation.value();
        int begin = 0;
        if (path.charAt(0) == '/') {
            begin = 1;
        }
        int end = path.length();
        if (path.charAt(end - 1) == '/') {
            end -= 1;
        }
        return path.substring(begin, end);
    }
}
