[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/OfficeDev/ews-java-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)  
[![Build Status](https://travis-ci.org/OfficeDev/ews-java-api.svg)](https://travis-ci.org/OfficeDev/ews-java-api) [![codecov.io](https://codecov.io/github/OfficeDev/ews-java-api/coverage.svg?branch=master)](https://codecov.io/github/OfficeDev/ews-java-api?branch=master)


# Spanning

This project is a fork of the Ms Office EWS api
There are a few code changes.

#### Adding Dependencies
To depend using maven:

1. Go to https://jitpack.io and sign in with github (top right of the screen). You'll have to grant jitpack authorization
to do a few things (e.g., access private repos).

2. After you're logged in, go <a href="https://jitpack.io/private#auth">here</a> and click the "maven" tab. Copy the xml
into your ~/.m2/settings.xml file.

3. In the pom where you need to depend on a stitch module, add the jitpack repo to your repositories section:

  ```xml
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
  ```

4. Now, you can depend on the ews-java-api module by using `com.github.SpanningCloudApps` as the `groupId` and `ews-java-api`
as the `artifactId`. For `version`, use `tagName` to depend on the a tag/release.

  ```xml
  <dependency>
      <groupId>com.github.SpanningCloudApps</groupId>
      <artifactId>ews-java-api</artifactId>
      <version>2.1.2</version>
  </dependency>
  ```

# EWS JAVA API

Please see the [Getting Started Guide](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide) on our wiki for an introduction to this library.

## Using the library
Prebuilt JARs are available in the Maven Central repository, which are easy to use with your project. Note that currently, no stable version is available yet, only snapshots in the snapshots repository.

### Maven / Gradle
For Documentation on how to use _ews-java-api_ with maven or gradle please refer to [this section in our wiki](https://github.com/OfficeDev/ews-java-api/wiki#maven--gradle-integration). 

### Building from source
To build a JAR from the source yourself, please see [this page](https://github.com/OfficeDev/ews-java-api/wiki/Building-EWS-JAVA-API).
