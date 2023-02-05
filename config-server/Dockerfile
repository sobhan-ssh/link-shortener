FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./target/config-server.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8012
ENTRYPOINT ["java","-jar","/app.jar"]


