#!/usr/bin/env bash

docker rmi sce/node-service-consumer:1.0.0
docker rmi sce/node-service-consumer:latest
docker rmi sce/node-service-provider:1.0.0
docker rmi sce/node-service-provider:latest
docker rmi sce/node-eureka:1.0.0
docker rmi sce/node-eureka:latest
docker rmi sce/node-apigateway:1.0.0
docker rmi sce/node-apigateway:latest
docker rmi sce/node-configuration:1.0.0
docker rmi sce/node-configuration:latest

mvn -f ./pom.xml install
mvn -f ./node-eureka/pom.xml            docker:build
mvn -f ./node-service-provider/pom.xml  docker:build
mvn -f ./node-service-consumer/pom.xml  docker:build
mvn -f ./node-apigateway/pom.xml        docker:build
mvn -f ./node-configuration/pom.xml     docker:build

mvn clean -q
