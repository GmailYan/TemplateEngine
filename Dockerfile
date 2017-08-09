FROM openjdk:8-jdk-alpine

# Spring Boot application creates working directories for Tomcat by default.
VOLUME /tmp

ADD target/TemplateEngine-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

EXPOSE 8080

# Tomcat use SecureRandom during slow startup, the entropy source urandom is going to speed it up
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]