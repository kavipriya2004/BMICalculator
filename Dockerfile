# Use an official Maven image to build the application
FROM maven:3.8.6-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven POM file and source code to the container
COPY pom.xml /app
COPY src /app/src

# Build the application
RUN mvn clean package

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/bmi-calculator-1.0-SNAPSHOT.jar /app/bmi-calculator.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/bmi-calculator.jar"]
