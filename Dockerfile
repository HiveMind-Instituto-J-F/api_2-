# Stage 1: Build do JAR
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Imagem final
FROM openjdk:17-jdk-alpine
WORKDIR /API_Rest
COPY --from=build /app/target/API_Rest-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD ["java", "-jar", "API_Rest-0.0.1-SNAPSHOT.jar"]