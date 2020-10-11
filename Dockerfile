FROM openjdk:13-alpine

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 80 8080
ENTRYPOINT ["java","-jar","/app.jar"]
