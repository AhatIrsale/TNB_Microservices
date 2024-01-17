# Stage 1: Build with Maven
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

# Stage 2: Create the final image

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/gateway.jar"]


