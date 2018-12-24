#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.profiles.active=docker
