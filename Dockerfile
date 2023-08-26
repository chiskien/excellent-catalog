

FROM eclipse-temurin:20
#Ubuntu base image with eclipse temurin distribution of pre-JRE installed
LABEL authors="chisk"
WORKDIR workspace
# Change the current dir to "workspace"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} catalog-service.jar

ENTRYPOINT ["java", "-jar", "excellent-bookshop:0.0.1-SNAPSHOT.jar"]
