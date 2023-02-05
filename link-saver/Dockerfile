FROM openjdk:8-jdk-alpine
FROM maven
COPY pom.xml .
COPY src ./src
RUN mvn clean install
ENTRYPOINT ["java","-jar","target/link-saver-service.jar"]


