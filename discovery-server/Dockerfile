FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./target/discovery-server.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]


