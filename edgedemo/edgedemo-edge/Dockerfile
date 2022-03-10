FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /common-sample/lib

COPY target/common-edge-0.0.1-SNAPSHOT.jar  /common-sample/

COPY target/lib /common-sample/lib

ENTRYPOINT java -jar /common-sample/common-edge-0.0.1-SNAPSHOT.jar