/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * The annotation for your JUnit5 test methods.
 *
 * @since 0.0.1
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JucsProvider.class)
public @interface ClasspathSource {

    /**
     * The directory in classpath.
     * @return Path in classpath, e.g. "org/example/packs/"
     */
    String value();

    /**
     * The glob to search for (don't use just {@code "**"}, always
     * add extension, like {@code "**.txt"}!).
     * @return Glob searching pattern, e.g. "**&#47;*.txt"
     */
    String glob() default "**/*.txt";

}
