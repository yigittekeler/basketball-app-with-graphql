FROM openjdk:17-jdk-slim

EXPOSE 8082
WORKDIR /app
ARG JAR_FILE=target/basketball-app-with-graphql-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app/app.jar

ENTRYPOINT ["java", "-jar","app/app.jar"]