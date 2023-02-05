FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./target/shortener-service.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9595
ENTRYPOINT ["java","-jar","/app.jar"]


