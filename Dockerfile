# Use OpenJDK 11 as the base image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot executable JAR into the container
COPY target/Employee-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to access the web app
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]
