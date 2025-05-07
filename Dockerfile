# Use a base JDK image
FROM openjdk:21-jdk-slim

# Set workdir
WORKDIR /app

# Copy the built JAR (adjust name accordingly)
COPY target/*.jar app.jar

# Expose port if your app uses 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]