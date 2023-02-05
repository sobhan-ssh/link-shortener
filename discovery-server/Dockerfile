FROM openjdk:8-jdk-alpine
FROM maven
COPY pom.xml .
COPY src ./src
RUN mvn clean install
EXPOSE 8761
ENTRYPOINT ["java","-jar","target/discovery-server.jar"]


