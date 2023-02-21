FROM openjdk:11.0.11-jre-slim

ENV APP_FILE target/identity-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /app

EXPOSE 8080

COPY $APP_FILE $APP_HOME/app.jar
COPY env.docker.properties $APP_HOME/env.properties
RUN mkdir $APP_HOME/configuration/
COPY configuration/logback.xml $APP_HOME/configuration/logback.xml

WORKDIR $APP_HOME
CMD ["java", "-jar", "app.jar"]

