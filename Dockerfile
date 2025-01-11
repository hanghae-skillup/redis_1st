# 1. Build Stage: JDK 21 기반 빌드
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Gradle 설정 및 소스 복사
COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle .
COPY build.gradle .
COPY module-app ./module-app
COPY module-rds-repo ./module-rds-repo
COPY module-service ./module-service
COPY module-core ./module-core

# Gradle 의존성 다운로드 및 빌드
RUN ./gradlew --no-daemon clean build -x test

# 2. Runtime Stage: 빌드된 JAR 실행
FROM eclipse-temurin:21-jre

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /app/module-app/build/libs/*.jar app.jar

# Spring Profile 설정 (prod로 고정)
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "app.jar"]
