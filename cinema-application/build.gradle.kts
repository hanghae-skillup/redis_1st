plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.hanghae"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":cinema-domain")) // 도메인 계층 의존성
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.0") // Java, JSON 변환 라이브러리 
}

tasks.test {
    useJUnitPlatform()
}