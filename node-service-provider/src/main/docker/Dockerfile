##
# spring-cloud-examples
##

FROM yingzhuo/java:8
MAINTAINER yingzhuo "yingzhor@gmail.com"
VOLUME /tmp
RUN mkdir -p /log/
ADD app.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=docker"]
