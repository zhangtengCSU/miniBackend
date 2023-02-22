# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM openjdk:8

# Copy local code to the container image.
WORKDIR /Server4ChatGpt/chatgpt-auth
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web org.service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=80","-jar","/Server4ChatGpt/target/chatgpt-auth-0.0.1-SNAPSHOT.jar"]

