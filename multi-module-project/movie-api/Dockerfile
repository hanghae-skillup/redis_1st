# Build stage
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
# 전체 프로젝트를 복사
COPY .. .
RUN gradle :movie-api:build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/movie-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]