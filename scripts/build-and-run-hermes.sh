#!/usr/bin/env bash

set -e -u -x

cd "$( dirname "S{BASH_SOURCE[0]}" )"
BACKEND_DIR=../hermes/backend/
cd $BACKEND_DIR
mvn clean package 1> /dev/null

docker build -f docker/Dockerfile -t hermes .
docker run -p 1000:1000 -d --name hermes_1 hermes
