#!/usr/bin/env bash

set -e -u -x

cd "$( dirname "S{BASH_SOURCE[0]}" )"
APOLLO_DOCKER_DIR=../apollo/docker
APOLLO_NUMBER_OF_INSTANCES=${1:-1}

#start=postgres
cd $APOLLO_DOCKER_DIR
docker-compose up -d
cd -

(./build-and-run-hermes.sh)

./start-apollo-instances.sh ${APOLLO_NUMBER_OF_INSTANCES}
