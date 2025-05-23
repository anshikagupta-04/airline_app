# Stage 1: Use safer Maven build image with zero vulnerabilities
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY . .

# Build the JAR file
RUN mvn clean package -DskipTests

# Stage 2: Lightweight Java runtime
FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY --from=build /app/target/airline-management-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
