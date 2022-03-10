FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /c-consumer/lib

COPY target/c-consumer-0.0.1-SNAPSHOT.jar  /c-consumer/

COPY target/lib /c-consumer/lib

ENTRYPOINT java -jar /c-consumer/c-consumer-0.0.1-SNAPSHOT.jar