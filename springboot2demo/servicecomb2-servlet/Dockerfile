FROM openjdk:8u181-jdk-alpine

RUN mkdir -p /servlet-jar/lib

COPY target/servicecomb-springboot2-servlet-jar-0.0.1-SNAPSHOT.jar  /servlet-jar/

COPY target/lib /servlet-jar/lib

ENTRYPOINT java -jar /servlet-jar/servicecomb-springboot2-servlet-jar-0.0.1-SNAPSHOT.jar