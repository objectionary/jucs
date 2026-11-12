/*
 * SPDX-FileCopyrightText: Copyright (c) 2022-2026 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.jucs;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

/**
 * Tests for globs that also match directory entries.
 *
 * @since 1.0
 */
final class JucsProviderRecursiveTest {

    @ParameterizedTest
    @ClasspathSource(value = "recursive-globs", glob = "**")
    void traversesMatchingDirectories(final String content, final String path) {
        Assertions.assertIterableEquals(
            Arrays.asList("nested fixture", "deep/inner/case.txt"),
            Arrays.asList(content.trim(), path),
            "A matching directory must expose its nested fixture and relative path"
        );
    }

    @ParameterizedTest
    @ClasspathSource(value = "recursive-globs", glob = "**/*")
    void traversesMatchingNestedDirectories(final String content, final String path) {
        Assertions.assertIterableEquals(
            Arrays.asList("nested fixture", "deep/inner/case.txt"),
            Arrays.asList(content.trim(), path),
            "A matching directory must expose its nested fixture and relative path"
        );
    }
}
