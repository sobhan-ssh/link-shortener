FROM openjdk:8-jdk-alpine
FROM maven
COPY pom.xml .
COPY src ./src
RUN mvn clean install
EXPOSE 9595
ENTRYPOINT ["java","-jar","target/shortener-service.jar"]

