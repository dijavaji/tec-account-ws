#define base docker image
#FROM openjdk:8-jdk-alpine
FROM amazoncorretto:11-alpine-jdk

#WORKDIR /app
#COPY ./target/tec-account-ws-0.0.1-SNAPSHOT.jar /app

#EXPOSE 8080

#CMD ["java", "-jar", "tec-account-ws-0.0.1-SNAPSHOT.jar"]
LABEL maintainer="technoloqie.com"
ADD target/tec-account-ws-0.0.1-SNAPSHOT.jar tec-account-ws.jar
ENTRYPOINT ["java", "-jar", "tec-account-ws.jar"]
