This version is based on EWS - release 2.0 in github

1. In the IntelliJ, set the module SDK to `Android API 28 Platform` instead of `1.8` or others
2. Correct the path for classes repacked jar

### Building JAR
To build the jar you need to run the `mvn clean` atleast once. This will install the classes repacked library to your local repo. Then you can run `mvn clean install` anytime.
Then copy the jar to android app.


Sources:
1. https://hc.apache.org/httpcomponents-client-4.5.x/android-port.html
2. https://github.com/smarek/httpclient-android/wiki/Project-Introduction