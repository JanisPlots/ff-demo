FROM java:openjdk-8-jdk

MAINTAINER janis.plots@gmail.com

ENV REST_APP_WORK=/app
COPY ff-demo-0.0.1-SNAPSHOT.jar ${REST_APP_WORK}/app.jar
EXPOSE 8080

CMD exec java \
  -Djava.security.egd=file:/dev/./urandom \
  ${JAVA_OPTS} \
  -jar ${REST_APP_WORK}/app.jar
