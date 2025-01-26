# 1단계: 빌드 이미지
FROM gradle:8.12-jdk21 AS build
WORKDIR /app

# Gradle 캐시를 활용하기 위해 먼저 주요 설정 파일 복사
COPY settings.gradle.kts build.gradle.kts /app/
COPY gradle /app/gradle/

# 나머지 프로젝트 파일 복사
COPY . /app/

# Gradle 빌드 실행 (core 모듈의 JAR 파일 생성)
RUN gradle core:bootJar -x test --no-daemon

# 2단계: 런타임 이미지
FROM eclipse-temurin:21-jdk-alpine AS runtime
WORKDIR /app

# 빌드된 JAR 파일 복사 (core 모듈 쪽 빌드 산출물)
COPY --from=build /app/core/build/libs/*.jar app.jar

# 환경 변수 설정
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# 애플리케이션 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
