#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

TAG="sce/node-eureka:latest"
mv ./node-eureka/target/app.jar ./app.jar
cp ./node-eureka/src/main/docker/docker-entrypoint.sh ./docker-entrypoint.sh
docker build -t ${TAG} .
unset TAG
rm ./*.jar
rm ./docker-entrypoint.sh


TAG="sce/node-configuration:latest"
mv ./node-configuration/target/app.jar ./app.jar
cp ./node-configuration/src/main/docker/docker-entrypoint.sh ./docker-entrypoint.sh
docker build -t ${TAG} .
unset TAG
rm ./*.jar
rm ./docker-entrypoint.sh

TAG="sce/node-service-provider:latest"
mv ./node-service-provider/target/app.jar ./app.jar
cp ./node-service-provider/src/main/docker/docker-entrypoint.sh ./docker-entrypoint.sh
docker build -t ${TAG} .
unset TAG
rm ./*.jar
rm ./docker-entrypoint.sh

TAG="sce/node-service-consumer:latest"
mv ./node-service-consumer/target/app.jar ./app.jar
cp ./node-service-consumer/src/main/docker/docker-entrypoint.sh ./docker-entrypoint.sh
docker build -t ${TAG} .
unset TAG
rm ./*.jar
rm ./docker-entrypoint.sh

TAG="sce/node-apigateway:latest"
mv ./node-apigateway/target/app.jar ./app.jar
cp ./node-apigateway/src/main/docker/docker-entrypoint.sh ./docker-entrypoint.sh
docker build -t ${TAG} .
unset TAG
rm ./*.jar
rm ./docker-entrypoint.sh

docker images | grep '<none>' | awk -F' ' '{print $3}' | xargs docker rmi > /dev/null

mvn clean -q
