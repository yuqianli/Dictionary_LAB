#!/bin/bash

USER=`whoami`

export HADOOP_HOME=/opt/mapr/hadoop/hadoop-0.20.2
export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native/Linux-amd64-64
export CLASSPATH=$HADOOP_HOME/lib/*:$HADOOP_HOME/*:/user/$USER/5/Dictionary_LAB/*
export HADOOP_CLASSPATH=$CLASSPATH

javac -d classes DictionaryMapper.java
javac -d classes DictionaryReducer.java
jar -cvf Dictionary.jar -C classes/ .
javac -classpath $CLASSPATH:Dictionary.jar -d classes DictionaryDriver.java
jar -cvf Dictionary.jar -C classes/ .
jar -cvf Dictionary.jar -C classes/ .
