FROM openjdk:17-jdk-alpine
LABEL authors="danielsousa-ieg"

WORKDIR /API_Rest

COPY target/API_Rest-0.0.1-SNAPSHOT.jar API_Rest-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "API_Rest-0.0.1-SNAPSHOT.jar"]
