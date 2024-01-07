FROM openjdk:17

COPY .env .env

LABEL maintainer="bankserver"

ADD target/bankserver-0.0.1-SNAPSHOT.jar bankServer.jar

ENTRYPOINT ["java", "-jar","bankServer.jar"]