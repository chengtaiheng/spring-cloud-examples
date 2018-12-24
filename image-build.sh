#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

read -p "您是否要删除所有旧的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    docker image ls | grep 'sce/' | awk -F ' ' '{print $3}' | xargs docker rmi --force
fi

read -p "您是否需要构建'node-eureka'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-eureka:latest"
    mv ./node-eureka/target/app.jar ./app.jar
    cp ./node-eureka/src/main/docker/Dockerfile ./Dockerfile
    cp ./node-eureka/src/main/docker/entrypoint.sh ./entrypoint.sh
    docker build -t ${TAG} .

    unset TAG
    rm ./*.jar
    rm ./Dockerfile
    rm ./entrypoint.sh
fi
unset build

read -p "您是否需要构建'node-configuration'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-configuration:latest"
    mv ./node-configuration/target/app.jar ./app.jar
    cp ./node-configuration/src/main/docker/Dockerfile ./Dockerfile
    cp ./node-configuration/src/main/docker/entrypoint.sh ./entrypoint.sh
    docker build -t ${TAG} .

    unset TAG
    rm ./*.jar
    rm ./Dockerfile
    rm ./entrypoint.sh
fi
unset build

read -p "您是否需要构建'node-service-provider'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-service-provider:latest"
    mv ./node-service-provider/target/app.jar ./app.jar
    cp ./node-service-provider/src/main/docker/Dockerfile ./Dockerfile
    cp ./node-service-provider/src/main/docker/entrypoint.sh ./entrypoint.sh
    docker build -t ${TAG} .

    unset TAG
    rm ./*.jar
    rm ./Dockerfile
    rm ./entrypoint.sh
fi
unset build

read -p "您是否需要构建'node-service-consumer'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-service-consumer:latest"
    mv ./node-service-consumer/target/app.jar ./app.jar
    cp ./node-service-consumer/src/main/docker/Dockerfile ./Dockerfile
    cp ./node-service-consumer/src/main/docker/entrypoint.sh ./entrypoint.sh
    docker build -t ${TAG} .

    unset TAG
    rm ./*.jar
    rm ./Dockerfile
    rm ./entrypoint.sh
fi
unset build

read -p "您是否需要构建'node-apigateway'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-apigateway:latest"
    mv ./node-apigateway/target/app.jar ./app.jar
    cp ./node-apigateway/src/main/docker/Dockerfile ./Dockerfile
    cp ./node-apigateway/src/main/docker/entrypoint.sh ./entrypoint.sh
    docker build -t ${TAG} .

    unset TAG
    rm ./*.jar
    rm ./Dockerfile
    rm ./entrypoint.sh
fi
unset build

docker images | grep '<none>' | awk -F' ' '{print $3}' | xargs docker rmi > /dev/null
mvn clean -q
