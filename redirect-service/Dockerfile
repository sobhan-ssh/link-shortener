FROM openjdk:8-jdk-alpine
FROM maven
COPY pom.xml .
COPY src ./src
RUN mvn clean install
EXPOSE 6565
ENTRYPOINT ["java","-jar","target/redirect-service.jar"]


