FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /c-provider/lib

COPY target/c-provider-0.0.1-SNAPSHOT.jar  /c-provider/

COPY target/lib /c-provider/lib

ENTRYPOINT java -jar /c-provider/c-provider-0.0.1-SNAPSHOT.jar