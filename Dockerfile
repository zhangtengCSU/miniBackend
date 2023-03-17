# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /miniBackend
COPY pom.xml ./
WORKDIR chatgpt-auth
COPY ./chatgpt-auth/pom.xml ./
COPY ./chatgpt-auth/src ./src

# Build a release artifact.

RUN mvn package -DskipTests

# Run the web org.service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/miniBackend/chatgpt-auth/target/chatgpt-auth-0.0.1-SNAPSHOT.jar"]