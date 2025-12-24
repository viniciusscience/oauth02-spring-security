#!/bin/bash

mvn clean package
docker build -t auth-gateway .
