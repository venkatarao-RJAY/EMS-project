#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/Gateway_EKA-1-0.0.1-SNAPSHOT.jar /app/Gateway.jar

# Expose port 2030
EXPOSE 2030

# Specify the command to run the jar file on port 2030
CMD ["java", "-jar", "Gateway.jar", "--server.port=2030"]

