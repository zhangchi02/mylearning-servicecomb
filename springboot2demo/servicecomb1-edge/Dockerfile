FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /edge/lib

COPY target/servicecomb-springboot2-edge-0.0.1-SNAPSHOT.jar  /edge/

COPY target/lib /edge/lib

ENTRYPOINT java -jar /edge/servicecomb-springboot2-edge-0.0.1-SNAPSHOT.jar