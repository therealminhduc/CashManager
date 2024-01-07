FROM openjdk:17

COPY .env .env

LABEL maintainer="bankserver"

ADD target/server-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-jar","server.jar"]