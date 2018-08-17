#!/bin/bash

if [ `uname -s` = Linux ] ; then
  HIGHEST_PRIORITY_JAVA_8=`update-alternatives --display javac | grep priority | grep -E 'java-8|1\.8' | sort -g -k 4 | tail -1 | cut -d\  -f1`
  if [ -e "$HIGHEST_PRIORITY_JAVA_8" ] ; then
    export JAVA_HOME="${HIGHEST_PRIORITY_JAVA_8%/bin/javac}"
  elif ! $JAVA_HOME/bin/java -version 2>&1 | head -n 1 | grep "1\.8" >> /dev/null ; then
    echo "Please set JAVA_HOME to version 1.8"
    exit
  fi
else
  if [ `uname -s` = Darwin ] ; then
    export JAVA_HOME=`/usr/libexec/java_home -F -v1.8*`
  else
    export JAVA_HOME=/usr
  fi
fi
JH=$JAVA_HOME

export PATH=$JH/bin:$PATH

if [ ! -f $BUILD_NUMBER ] ; then
  JAVA_OPTS="-Dsbt.log.noformat=true"
fi

sbt $JAVA_OPTS "$@"
