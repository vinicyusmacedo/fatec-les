#!/bin/sh
classpath=".:$(pwd)/src/jars/iohelper.jar"
cd src && javac -classpath $classpath view/Main.java