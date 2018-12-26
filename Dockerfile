# ----------------------------------------------------------------------------------------------------------------------
# spring-cloud-examples
# ----------------------------------------------------------------------------------------------------------------------

FROM yingzhuo/java:8

USER root

MAINTAINER yingzhuo yingzhor@gmail.com

VOLUME /tmp

VOLUME /log

ADD app.jar app.jar

ADD docker-entrypoint.sh docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
