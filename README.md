This Repository contains the source-code for all chapters of the [Netty in Action Book](http://manning.com/maurer).

Enjoy and feedback / PR's welcome!

## Pre-requisites

- JDK 1.7.0u71 or better
- Maven 3.2.3 or better

## Troubleshooting

- If you're hitting a `NoSuchMethodError` when starting chapter 12's SPDY server,
  edit `pom.xml` and change the version of `npn.boot.version` to one that matches
  your JDK's version in [this table](http://www.eclipse.org/jetty/documentation/current/npn-chapter.html#npn-versions).
