#!/usr/bin/env bash

docker rmi sce/node-service-consumer:1.0.0
docker rmi sce/node-service-consumer:latest
docker rmi sce/node-service-provider:1.0.0
docker rmi sce/node-service-provider:latest
docker rmi sce/node-eureka:1.0.0
docker rmi sce/node-eureka:latest
docker rmi sce/node-apigateway:1.0.0
docker rmi sce/node-apigateway:latest

mvn -f ./node-eureka/pom.xml clean package docker:build
mvn -f ./node-service-provider/pom.xml clean package docker:build
mvn -f ./node-service-consumer/pom.xml clean package docker:build
mvn -f ./node-apigateway/pom.xml clean package docker:build

mvn clean -q
