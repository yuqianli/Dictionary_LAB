#!/bin/bash

USER=`whoami`

export HADOOP_HOME=/opt/mapr/hadoop/hadoop-0.20.2
export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native/Linux-amd64-64
export CLASSPATH=$HADOOP_HOME/lib/*:$HADOOP_HOME/*:/user/$USER/Dictionary_LAB/*
export HADOOP_CLASSPATH=$CLASSPATH

rm -rf /user/$USER/Dictionary_LAB/OUT


hadoop jar Dictionary.jar Dictionary.DictionaryDriver /user/$USER/Dictionary_LAB/DATA/ /user/$USER/Dictionary_LAB/OUT
