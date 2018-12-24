##
# spring-cloud-examples
##

FROM yingzhuo/java:8
MAINTAINER yingzhuo yingzhor@gmail.com
VOLUME /tmp
ADD app.jar app.jar
ADD entrypoint.sh entrypoint.sh
RUN mkdir -p /log/ && \
    chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
