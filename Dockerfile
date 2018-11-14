##
# spring-cloud-examples
##

FROM yingzhuo/java:8
MAINTAINER yingzhuo "yingzhor@gmail.com"
VOLUME /tmp
ADD app.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
CMD ["--spring.profiles.active=default,docker"]
