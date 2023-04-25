FROM openjdk:11.0.11-jre-slim

ENV APP_FILE target/api-gateway-0.0.1-SNAPSHOT.jar
ENV APP_HOME /app

EXPOSE 8080

COPY $APP_FILE $APP_HOME/app.jar
COPY env.docker.properties $APP_HOME/env.properties

WORKDIR $APP_HOME
CMD ["java", "-jar", "app.jar"]

