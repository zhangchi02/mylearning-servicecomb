FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /vertx-jar/lib

COPY target/servicecomb-springboot2-vertx-jar-0.0.1-SNAPSHOT.jar  /vertx-jar/

COPY target/lib /vertx-jar/lib

ENTRYPOINT java -jar /vertx-jar/servicecomb-springboot2-vertx-jar-0.0.1-SNAPSHOT.jar