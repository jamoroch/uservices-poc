#!/usr/bin/env bash
cd "$( dirname "S{BASH_SOURCE[0]}" )"
NUMBER_OF_PROCESSES=${1:-2}
BASE_PORT=${2:-2000}
BACKEND_DIR=../apollo/backend/
TARGET_DIRNAME=target
TARGET_DIR=$BACKEND_DIR
TARGET_DIR+=$TARGET_DIRNAME
APOLLO_VERSION=1
JAVA_OPTS="-Xmx1024m -XX:MaxMetaspaceSize=128m"

cd $BACKEND_DIR
mvn clean package
cd -



for i in $(seq $NUMBER_OF_PROCESSES)
  do
    PORT=$(($BASE_PORT + $i))
    rm -f ../run/$i/logs/*
    exec nohup java -jar $JAVA_OPTS -Dserver.port=$PORT -Dlogging.file=../run/$i/logs/server.log $TARGET_DIR/apollo-$APOLLO_VERSION.jar  2>&1 > /dev/null &
  done

