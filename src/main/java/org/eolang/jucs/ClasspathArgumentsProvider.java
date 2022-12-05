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

import java.util.Collection;
import java.util.LinkedList;
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
final class ClasspathArgumentsProvider implements ArgumentsProvider,
    AnnotationConsumer<ClasspathSource> {

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
        final String dir = this.annotation.value();
        return ClasspathArgumentsProvider.yamls(dir, "")
            .stream()
            .map(
                p -> Arguments.of(
                    new Pack.Default(
                        new UncheckedText(
                            new TextOf(
                                new ResourceOf(
                                    String.format("%s/%s", dir, p)
                                )
                            )
                        ).asString()
                    )
                )
            );
    }

    /**
     * Find all YAMLs recursively on classpath.
     * @param path The value
     * @param prefix Prefix (empty when it starts)
     * @return The list of full paths
     */
    private static Collection<String> yamls(final String path,
        final String prefix) {
        final Collection<String> out = new LinkedList<>();
        final String folder = new UncheckedText(
            new TextOf(new ResourceOf(path))
        ).asString();
        final String[] paths = folder.split("\n");
        for (final String sub : paths) {
            if (sub.endsWith(".yaml")) {
                out.add(String.format("%s%s", prefix, sub));
            } else {
                out.addAll(
                    ClasspathArgumentsProvider.yamls(
                        String.format("%s%s/", path, sub),
                        String.format("%s/", sub)
                    )
                );
            }
        }
        return out;
    }
}
