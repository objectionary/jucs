[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/jucs)](http://www.rultor.com/p/objectionary/jucs)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/objectionary/jucs/actions/workflows/mvn.yml/badge.svg)](https://github.com/objectionary/jucs/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=objectionary/jucs)](http://www.0pdd.com/p?name=objectionary/jucs)
[![Maven Central](https://img.shields.io/maven-central/v/com.objectionary/jucs.svg)](https://maven-badges.herokuapp.com/maven-central/com.objectionary/jucs)
[![Javadoc](http://www.javadoc.io/badge/com.objectionary/jucs.svg)](http://www.javadoc.io/doc/com.objectionary/jucs)
[![codecov](https://codecov.io/gh/objectionary/jucs/branch/master/graph/badge.svg)](https://codecov.io/gh/objectionary/jucs)
[![Hits-of-Code](https://hitsofcode.com/github/objectionary/jucs)](https://hitsofcode.com/view/github/objectionary/jucs)
![Lines of code](https://img.shields.io/tokei/lines/github/objectionary/jucs)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/objectionary/jucs/blob/master/LICENSE.txt)

There is a simple annotation in this package, which may help you
turn files in classpath into sources of a JUnit5 test method.

First, add this to your `pom.xml`:

```xml
<dependency>
  <groupId>org.eolang</groupId>
  <artifactId>jucs</artifactId>
</dependency>
```

Then, to iterate over `*.yaml` files in `src/test/resources/org/example/` directory:

```java
import org.eolang.jucs.ClasspathSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

final class SimpleTest { 
    @ParameterizedTest
    @ClasspathSource(value="org/example/", glob="**/*.yaml")
    void simpleTest(String y) {
        // In the "y" variable is the content of the YAML file
    }
}
```

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
