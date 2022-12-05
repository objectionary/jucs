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
