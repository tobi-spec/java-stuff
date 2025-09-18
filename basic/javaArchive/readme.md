## make output directory

```bash
mkdir out
```

## compile class

```bash
javac .\HelloWorld.java -d out
```

## packaging archive
JAR files are just Zips conatining the compiles classes

```bash
jar cf mylib.jar -C out .
```

## run with jar

````bash
java -cp ".;C:\Users\tobias.weiland\projects\java-stuff\basic\javaArchive\mylib.jar" basic.javaArchive.UseHelloWorld
````