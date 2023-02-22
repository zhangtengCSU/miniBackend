# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /Server4ChatGpt
COPY chatgpt-auth ./chatgpt-auth
COPY chatgpt-model ./chatgpt-model
COPY chatgpt-web ./chatgpt-web

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=80","-jar","/Server4ChatGpt/target/Server4ChatGpt-0.0.1-SNAPSHOT.jar"]

