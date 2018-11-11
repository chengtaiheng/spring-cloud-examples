##
# spring-cloud-examples
##

FROM openjdk:8u171-alpine
MAINTAINER yingzhuo "yingzhor@gmail.com"
VOLUME /tmp
ADD node-apigateway.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
CMD ["--spring.profiles.active=default,docker"]
