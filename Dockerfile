FROM eclipse-temurin:17
LABEL authors="chisk"
WORKDIR workspace

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} catalog-service.jar

ENTRYPOINT ["java", "-jar", "catalog-service.jar"]
