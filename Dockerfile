# Stage 1: Build with Maven
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy project files
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:17
WORKDIR /app

# Create volume to persist H2 database file
VOLUME /data/h2

# Copy JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
