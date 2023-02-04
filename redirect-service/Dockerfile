FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 6565
ENTRYPOINT ["java","-jar","/app.jar"]


