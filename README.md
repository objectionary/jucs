[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/jucs)](http://www.rultor.com/p/objectionary/jucs)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/objectionary/jucs/actions/workflows/mvn.yml/badge.svg)](https://github.com/objectionary/jucs/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=objectionary/jucs)](http://www.0pdd.com/p?name=objectionary/jucs)
[![Maven Central](https://img.shields.io/maven-central/v/org.eolang/jucs.svg)](https://maven-badges.herokuapp.com/maven-central/org.eolang/jucs)
[![Javadoc](http://www.javadoc.io/badge/org.eolang/jucs.svg)](http://www.javadoc.io/doc/org.eolang/jucs)
[![codecov](https://codecov.io/gh/objectionary/jucs/branch/master/graph/badge.svg)](https://codecov.io/gh/objectionary/jucs)
[![Hits-of-Code](https://hitsofcode.com/github/objectionary/jucs)](https://hitsofcode.com/view/github/objectionary/jucs)
![Lines of code](https://img.shields.io/tokei/lines/github/objectionary/jucs)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/objectionary/jucs/blob/master/LICENSE.txt)

There is a simple [`@ClasspathSource`](https://www.javadoc.io/doc/org.eolang/jucs/latest/org/eolang/jucs/ClasspathSource.html) 
annotation in this package. It may help you
turn files, which are available in classpath, into sources of a JUnit5 test method.

First, add this to your `pom.xml`:

```xml
<dependency>
  <groupId>org.eolang</groupId>
  <artifactId>jucs</artifactId>
</dependency>
```

Then, to iterate over the `*.yaml` files in the `src/test/resources/org/example/` 
directory (assuming you use Maven or Gradle):

```java
import org.eolang.jucs.ClasspathSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

final class SimpleTest { 
    @ParameterizedTest
    @ClasspathSource(value="/org/example", glob="**.yaml")
    void simpleTest(String yaml) {
        // In the "yaml" variable is the content of the YAML file
    }
}
```

The `simpleTest()` method will be called a number of times, every time
getting the content of the next YAML file inside the `yaml` variable. 

We use this library in `PacksTest`, `OptimizeMojoTest` and `SnippetsTest`
in the [objectionary/eo](https://github.com/objectionary/eo) repository.

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
