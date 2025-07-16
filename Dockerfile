# Use a base image with Java
FROM openjdk:17-jdk-slim

# Directory name of Docker Container
WORKDIR /app

# Copy the built JAR file (adjust the path and name)
COPY build/libs/yjw7003-0.0.1-SNAPSHOT.jar yjw7003-0.0.1-SNAPSHOT.jar

# Set command to run the JAR
ENTRYPOINT ["java", "-jar", "yjw7003-0.0.1-SNAPSHOT.jar"]