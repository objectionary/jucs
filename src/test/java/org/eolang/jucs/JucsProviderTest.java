/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2026 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

/**
 * Simple test case.
 * @since 0.0.1
 * @checkstyle ProhibitLineSeparatorInStringsCheck (50 lines)
 */
final class JucsProviderTest {

    @ParameterizedTest
    @ClasspathSource("com/yegor256/jucs")
    void simpleTest(final String file) {
        Assertions.assertEquals(file, "Hello, world!\n");
    }

    @ParameterizedTest
    @ClasspathSource(value = "/com/yegor256/jucs", glob = "**/*.text")
    void findsInFolders(final String file) {
        Assertions.assertEquals(file, "hey!\n");
    }

    @ParameterizedTest
    @ClasspathSource(value = "com/yegor256/jucs/bar", glob = "**.text")
    void findsWithBigGlob(final String file) {
        Assertions.assertEquals(file, "hey!\n");
    }

    @ParameterizedTest
    @ClasspathSource(value = "com/yegor256/jucs/bar", glob = "*.text")
    void findsWithLocalGlob(final String file) {
        Assertions.assertEquals(file, "hey!\n");
    }

    @ParameterizedTest
    @ClasspathSource(value = "com/yegor256/jucs", glob = "**/baz/*.text")
    void findsInNestedFolders(final String file) {
        Assertions.assertEquals(file, "hey!\n");
    }
  
    @ClasspathSource(value = "com/yegor256/jucs/foo", glob = "*.txt")
    void exposesLocalPath(final String content, final String path) {
        Assertions.assertEquals("a.txt", path);
    }

    @ParameterizedTest
    @ClasspathSource(value = "com/yegor256/jucs", glob = "**/*.txt")
    void exposesNestedPath(final String content, final String path) {
        Assertions.assertEquals("foo/a.txt", path);
    }

    @ParameterizedTest
    @ClasspathSource(value = "com/yegor256/jucs", glob = "**/*.text")
    void exposesPathForTextFiles(final String content, final String path) {
        Assertions.assertTrue(Arrays.asList("x.text", "bar/y.text").contains(path));
    }
}
