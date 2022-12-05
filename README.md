[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/jucs)](http://www.rultor.com/p/yegor256/jucs)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/yegor256/jucs/actions/workflows/mvn.yml/badge.svg)](https://github.com/yegor256/jucs/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=yegor256/jucs)](http://www.0pdd.com/p?name=yegor256/jucs)
[![Maven Central](https://img.shields.io/maven-central/v/com.yegor256/jucs.svg)](https://maven-badges.herokuapp.com/maven-central/com.yegor256/jucs)
[![Javadoc](http://www.javadoc.io/badge/com.yegor256/jucs.svg)](http://www.javadoc.io/doc/com.yegor256/jucs)
[![codecov](https://codecov.io/gh/yegor256/jucs/branch/master/graph/badge.svg)](https://codecov.io/gh/yegor256/jucs)
[![Hits-of-Code](https://hitsofcode.com/github/yegor256/jucs)](https://hitsofcode.com/view/github/yegor256/jucs)
![Lines of code](https://img.shields.io/tokei/lines/github/yegor256/jucs)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/yegor256/jucs/blob/master/LICENSE.txt)

There is a simple annotation in this package, which may help you
turn files in classpath into sources of a JUnit test method.

First, add this to your `pom.xml`:

```xml
<dependency>
  <groupId>com.yegor256</groupId>
  <artifactId>jucs</artifactId>
</dependency>
```

Then, to iterate over `*.yaml` files in `src/test/resources/org/example/` directory:

```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

final class SimpleTest { 
    @ParameterizedTest
    @ClasspathSource(value="org/example", glob="**/*.yaml")
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
