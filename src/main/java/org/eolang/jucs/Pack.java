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

import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * The transformation.
 *
 * @since 0.0.1
 */
public interface Pack {

    /**
     * Try to transform XML and then match all XPath assertions.
     * @return TRUE if everything matches
     */
    boolean matches();

    /**
     * Get the entire YAML as a Map.
     * @return The map
     */
    Map<String, Object> map();

    /**
     * Default implementation.
     *
     * @since 0.0.1
     */
    final class Default implements Pack {

        /**
         * The YAML content.
         */
        private final String yaml;

        /**
         * Ctor.
         * @param yml The YAML script
         */
        public Default(final String yml) {
            this.yaml = yml;
        }

        @Override
        public boolean matches() {
            return true;
        }

        @Override
        public Map<String, Object> map() {
            return new Yaml().load(this.yaml);
        }

    }

}
