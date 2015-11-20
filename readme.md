[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/OfficeDev/ews-java-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)  
[![Build Status](https://travis-ci.org/OfficeDev/ews-java-api.svg)](https://travis-ci.org/OfficeDev/ews-java-api) [![codecov.io](https://codecov.io/github/OfficeDev/ews-java-api/coverage.svg?branch=master)](https://codecov.io/github/OfficeDev/ews-java-api?branch=master)


# Spanning

This project is a fork of the Ms Office EWS api
There are a few code changes.
This project is distributed via the Spanning Cloudbees maven repo
to update the repo

Mount the cloudbees repo locally via DAV
http://wiki.cloudbees.com/bin/view/DEV/Mounting+DAV+Repositories

Run:
mvn clean source:jar javadoc:jar deploy -DrepositoryId=local-dav


# EWS JAVA API

Please see the [Getting Started Guide](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide) on our wiki for an introduction to this library.

## Using the library
Prebuilt JARs are available in the Maven Central repository, which are easy to use with your project. Note that currently, no stable version is available yet, only snapshots in the snapshots repository.

### Maven / Gradle
For Documentation on how to use _ews-java-api_ with maven or gradle please refer to [this section in our wiki](https://github.com/OfficeDev/ews-java-api/wiki#maven--gradle-integration). 

### Building from source
To build a JAR from the source yourself, please see [this page](https://github.com/OfficeDev/ews-java-api/wiki/Building-EWS-JAVA-API).
