/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.jucs;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

/**
 * Provider of the sources.
 *
 * @since 0.0.1
 */
final class JucsProvider implements ArgumentsProvider,
    AnnotationConsumer<ClasspathSource> {

    /**
     * Is it a file?
     */
    private static final Pattern IS_FILE = Pattern.compile("^.+\\.[a-z]+$");

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
        final ExtensionContext ctx) {
        return this.yamls("")
            .stream()
            .map(
                p -> Arguments.of(
                    new UncheckedText(new TextOf(new ResourceOf(p))).asString()
                )
            );
    }

    /**
     * Find all YAMLs recursively on classpath.
     * @param prefix Prefix (empty when it starts)
     * @return The list of full paths
     */
    private Collection<String> yamls(final String prefix) {
        final Collection<String> out = new LinkedList<>();
        final String home = String.format("%s%s", this.annotation.value(), prefix);
        final String folder = new UncheckedText(
            new TextOf(new ResourceOf(home))
        ).asString();
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(
            String.format("glob:%s", this.annotation.glob())
        );
        final String[] subs = folder.split("\n");
        for (final String sub : subs) {
            final Path path = Paths.get(String.format("/%s%s", prefix, sub));
            if (matcher.matches(path)) {
                out.add(String.format("%s/%s", home, sub));
            } else if (!JucsProvider.IS_FILE.matcher(sub).matches()) {
                out.addAll(this.yamls(String.format("%s/", sub)));
            }
        }
        return out;
    }
}
