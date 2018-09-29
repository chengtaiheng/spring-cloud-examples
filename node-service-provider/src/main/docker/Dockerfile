##
# Playground
##

FROM openjdk:8u171-alpine
MAINTAINER yingzhuo "yingzhor@gmail.com"
VOLUME /tmp
ADD node-service-provider.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=default,docker"]
