#!/bin/bash

 mvn clean package -DskipTests
docker stop authservice || true
docker rm authservice || true

docker build -t authservice .
