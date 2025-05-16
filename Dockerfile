# Stage 1: Build the application using Maven
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the source code to the container
COPY . .

# Build the project (skip tests if needed)
RUN mvn clean package -DskipTests

# Stage 2: Create a smaller runtime image using JDK only
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (change if needed)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
