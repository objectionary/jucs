/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2026 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

/**
 * Tests for globs that also match directory entries.
 * @since 1.0
 */
final class JucsProviderRecursiveTest {
    @ParameterizedTest
    @ClasspathSource(value = "recursive-globs", glob = "**")
    void traversesMatchingDirectories(final String content, final String path) {
        Assertions.assertEquals("nested fixture", content.trim());
        Assertions.assertEquals("deep/inner/case.txt", path);
    }

    @ParameterizedTest
    @ClasspathSource(value = "recursive-globs", glob = "**/*")
    void traversesMatchingNestedDirectories(final String content, final String path) {
        Assertions.assertEquals("nested fixture", content.trim());
        Assertions.assertEquals("deep/inner/case.txt", path);
    }
}
