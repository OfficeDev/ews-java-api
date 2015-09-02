[![Build Status](https://travis-ci.org/OfficeDev/ews-java-api.svg)](https://travis-ci.org/OfficeDev/ews-java-api) [![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/OfficeDev/ews-java-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# EWS JAVA API

Please see the [Getting Started Guide](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide) on our wiki for an introduction to this library.

## Contributing
Please see the [HowTo:Contribute](CONTRIBUTING.md) for details.

## Using the library
Prebuilt JARs are available in the Maven Central repository, which are easy to use with your project. Note that currently, no stable version is available yet, only snapshots in the snapshots repository.

### Maven
If you want to use a snapshot build, add the Maven Central snapshots repository to your project's `pom.xml`. If you want to use a stable build (not available yet), you should skip this step.
```xml
<project>
  <repositories>
    <repository>
      <id>sonatype-snapshots</id>
      <name>Sonatype OSS Snapshots</name>
      <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
      <releases>
        <enabled>false</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
</project>
```

And finally, add the dependency to your project's `pom.xml`.
```xml
<project>
  <dependencies>
    <dependency>
      <groupId>com.microsoft.ews-java-api</groupId>
      <artifactId>ews-java-api</artifactId>
      <version>2.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
</project>
```

### Gradle
If you want to use a snapshot build, add the Maven Central snapshots repository to your project's `build.gradle`. If you want to use a stable build (not available yet), you should skip this step.
```groovy
repositories {
    maven {
        url 'https://oss.sonatype.org/content/repositories/snapshots/'
    }
}
```

And finally, add the dependency to your project's `build.gradle`.
```groovy
dependencies {
    compile 'com.microsoft.ews-java-api:ews-java-api:2.0-SNAPSHOT'
}
```

### Building from source
To build a JAR from the source yourself, please see [this page](https://github.com/OfficeDev/ews-java-api/wiki/Building-EWS-JAVA-API).
