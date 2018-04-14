# url-validation

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/fun.mike/url-validation/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fun.mike/url-validation)
[![Javadocs](https://www.javadoc.io/badge/fun.mike/url-validation.svg)](https://www.javadoc.io/doc/fun.mike/url-validation)

URL validation. Pulled from `commons-validator`.

## Example

```java
UrlValidator.http("http://e/law.k  noi").orThrow("Invalid URL");
=> java.lang.IllegalStateException("Invalid URL: Invalid path \"/law.k  noi\".")
```

## Build

[![CircleCI](https://circleci.com/gh/mike706574/java-url-validation.svg?style=svg)](https://circleci.com/gh/mike706574/java-url-validation)

## Copyright and License

This project is licensed under the terms of the Apache 2.0 license.
