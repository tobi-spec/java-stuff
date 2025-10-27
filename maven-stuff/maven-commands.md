# validate – validate the project is correct

# compile 
compile source code similar to

```bash
javac .\HelloWorld.java -d out
```

# test – run unit tests
Compiles all test source code and runs them via surefire plugin

# package 
package code into JAR/WAR similar to 

```bash
jar cf mylib.jar -C out .
```

# verify – run any checks

# install
install to local Maven repo (~/.m2)
copy all jars/wars to a common location for use as dependencies in other projects

# deploy – push to remote repo (e.g., Nexus, Artifactory)


## mvn versions:display-dependency-updates
searches for new updates for dependencies
needs plugin

## mvn help:effective-pom
Gives the effective POM for the project, 
which is the result of merging the project's POM with its parent POMs and applying any settings from the super POM.