FROM eclipse-temurin:11.0.14.1_1-jdk-alpine
COPY build/libs/*.jar /app/auth-server.jar
WORKDIR /app
# ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dockerdev", "auth-server.jar"]
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dockerdev", "auth-server.jar"]
#docker build -t ecram/auth-micro:0.0.1-SNAPSHOT .
#docker run -m 256m -d --name auth-server  -p 9000:9000 ecram/auth-micro:0.0.1-SNAPSHOT
#docker stop auth-server
#docker push ecram/auth-micro:0.0.1-SNAPSHOT
#docker pull ecram/auth-micro:0.0.1-SNAPSHOT