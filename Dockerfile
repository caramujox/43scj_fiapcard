FROM openjdk:18
MAINTAINER Caio de Araujo Morais
COPY build/libs/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar"]