FROM gradle:8.12.0-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle :movie-api:bootJar --no-daemon

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/movie-api/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]