#!/usr/bin/env bash

APOLLO_PID=$(ps aux | grep apollo-1.jar | grep java | awk '{print $2}')

kill -9 $APOLLO_PID
docker stop hermes_1
docker rm -f hermes_1
docker stop docker_apollo_postgres_1
