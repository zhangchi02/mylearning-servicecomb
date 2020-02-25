FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /common-provider/lib

COPY target/common-provider-0.0.1-SNAPSHOT.jar  /common-provider/

COPY target/lib /common-provider/lib

ENTRYPOINT java -jar /common-provider/common-provider-0.0.1-SNAPSHOT.jar