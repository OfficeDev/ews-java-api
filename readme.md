[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/OfficeDev/ews-java-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)  
[![Build Status](https://travis-ci.org/OfficeDev/ews-java-api.svg)](https://travis-ci.org/OfficeDev/ews-java-api) [![codecov.io](https://codecov.io/github/OfficeDev/ews-java-api/coverage.svg?branch=master)](https://codecov.io/github/OfficeDev/ews-java-api?branch=master)

NOTE: This fork was made to fix the 'hanging' close connection problem.
The solution was pulled from pexlabs, referenced here:
https://github.com/OfficeDev/ews-java-api/issues/425

To use this version in your build, change:

    compile 'com.microsoft.ews-java-api:ews-java-api:2.0'

to:

    compile 'com.github.mitimes:ews-java-api:master-SNAPSHOT'

using jitpack.io as a repo.

# EWS JAVA API

Please see the [Getting Started Guide](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide) on our wiki for an introduction to this library.

## Using the library
Please see [this wiki-entry](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide#using-the-library) on how to include the library in your project

### Maven / Gradle
For Documentation on how to use _ews-java-api_ with maven or gradle please refer to [this section in our wiki](https://github.com/OfficeDev/ews-java-api/wiki#maven--gradle-integration). 

### Building from source
To build a JAR from the source yourself, please see [this page](https://github.com/OfficeDev/ews-java-api/wiki/Building-EWS-JAVA-API).
