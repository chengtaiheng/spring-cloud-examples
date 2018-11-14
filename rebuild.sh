#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

read -p "您是否需要构建'node-eureka'的镜像? (y/n) " build
if [[ "y" == ${build} || "Y" == ${build} ]]; then
    TAG="sce/node-eureka:latest"
    mv ./node-eureka/target/node-eureka.jar ./app.jar
    cp ./node-eureka/src/main/docker/Dockerfile ./Dockerfile
    docker build -t ${TAG} .
    unset TAG
fi
unset build

#mvn clean -q
