#!/bin/sh

profiles=${SPRING_PROFILES}

if [[ -z "$profiles" ]]; then
    profiles=docker
fi

java -Djava.security.egd=file:/dev/./urandom \
    -jar /app.jar \
    --spring.profiles.active=docker
