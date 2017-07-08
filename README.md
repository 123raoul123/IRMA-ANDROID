# IRMA Android
The IRMA Android application is the Android implementation of [IRMA](https://github.com/123raoul123/irma).

## Prerequisites
This application has the following dependencies. All these dependencies are included in the project and are compiled by and loaded when building the code. The dependencies are located in /app/libs.

* [Relic](https://github.com/relic-toolkit/relic)
  * A forked version of Relic is used to support [Relic edit](https://github.com/sietseringers/relic)
  * Makefile contains an extra flag (${CMAKE_CXX_FLAGS}) to support NDK.
  
* [Java Native Access](https://github.com/java-native-access/jna)
  * JNA-min.jar is used. JNA-min.jar does not contain the precompiled libjnidispatch.so file. The precompiled versions are stored in /app/src/main/jniLibs/.
  
## Building


## Testing

