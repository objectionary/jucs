[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/jucs)](http://www.rultor.com/p/yegor256/jucs)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/yegor256/jucs/actions/workflows/mvn.yml/badge.svg)](https://github.com/yegor256/jucs/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=yegor256/jucs)](http://www.0pdd.com/p?name=yegor256/jucs)
[![Maintainability](https://api.codeclimate.com/v1/badges/742bde48ea6fabdba1ce/maintainability)](https://codeclimate.com/github/yegor256/jucs/maintainability)
[![Maven Central](https://img.shields.io/maven-central/v/com.yegor256/jucs.svg)](https://maven-badges.herokuapp.com/maven-central/com.yegor256/jucs)
[![Javadoc](http://www.javadoc.io/badge/com.yegor256/jucs.svg)](http://www.javadoc.io/doc/com.yegor256/jucs)
[![codecov](https://codecov.io/gh/yegor256/jucs/branch/master/graph/badge.svg)](https://codecov.io/gh/yegor256/jucs)
[![Hits-of-Code](https://hitsofcode.com/github/yegor256/jucs)](https://hitsofcode.com/view/github/yegor256/jucs)
![Lines of code](https://img.shields.io/tokei/lines/github/yegor256/jucs)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/yegor256/jucs/blob/master/LICENSE.txt)

It's a simple manager of "records" in a text file of CSV, JSON, etc. format.
It's something you would use when you don't want to run a full database, but
just a list of lines in a file is not enough. You need a file with structured
records.

You add this to your `pom.xml`:

```xml
<dependency>
  <groupId>com.yegor256</groupId>
  <artifactId>jucs</artifactId>
</dependency>
```

Then, to manage `books.csv` file:

```java
import org.eolang.jucs.MnCsv;
import org.eolang.jucs.TjDefault;
import org.eolang.jucs.Tojo;

Tojos jucs=new TjDefault(new MnCsv("books.csv"));
    Tojo t1=jucs.add("Object Thinking"); // unique ID
    t1.set("author","David West");
    Tojo t2=jucs.select(
    t->t.get("author").equals("David West")
    ).get(0);
```

Each record has a unique ID, which is also the first column.

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
