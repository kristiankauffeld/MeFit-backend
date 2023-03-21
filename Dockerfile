
# Use the official Gradle image as the base image
FROM gradle:7.6.1-jdk17 as build

# Set the working directory
WORKDIR /

# Copy the project files into the container
COPY . .

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the project using the Gradle wrapper
RUN ./gradlew clean build -x test --no-daemon

# Use the official OpenJDK image as the runtime base image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build container
COPY --from=build /build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]