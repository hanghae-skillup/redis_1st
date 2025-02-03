plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java-test-fixtures") // 테스트 픽스처 활성화
}

group = "com.hanghae"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}