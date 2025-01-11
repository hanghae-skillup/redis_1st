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
    implementation(project(":cinema-domain")) //도메인 계층 의존성
    implementation(project(":cinema-application")) // 애플리케이션 계층 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") //db
    runtimeOnly("com.h2database:h2") //db
    runtimeOnly("com.mysql:mysql-connector-j") //db
}

tasks.test {
    useJUnitPlatform()
}