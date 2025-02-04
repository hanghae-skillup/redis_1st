FROM openjdk:21-jdk

WORKDIR /app

COPY api/build/libs/*SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]