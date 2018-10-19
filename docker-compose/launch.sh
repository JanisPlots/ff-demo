#!/usr/bin/env bash

set -e

export base_dir=$(pwd)

cd ..

./mvnw package #-DskipTests=true
cp target/*.jar ${base_dir}/loan-rest-api/

cd ${base_dir}
docker-compose build
docker-compose up -d