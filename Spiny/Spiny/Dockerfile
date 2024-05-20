# Use a base image with JDK and Maven installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code files into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

# Use a lightweight base image for the application
FROM openjdk:23-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage to the container
COPY --from=build /app/target/Spiny-*.jar spiny.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "spiny.jar"]
