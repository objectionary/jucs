/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

/**
 * Simple test case.
 *
 * @since 0.0.1
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

}
