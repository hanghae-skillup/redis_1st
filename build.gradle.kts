plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.2.0" // 안정된 Spring Boot 버전
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}
dependencies {
	// Spring Boot Starter Dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
	implementation("org.springframework.boot:spring-boot-starter-web") // Spring Web
	implementation("org.springframework.boot:spring-boot-starter-validation") // Validation

	// Database Driver
	implementation("mysql:mysql-connector-java:8.0.33") // MySQL Driver (명시적 버전 추가)

	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.apache.commons:commons-pool2") // Redis 커넥션 풀

	// Kotlin Specific Dependencies
	implementation("org.jetbrains.kotlin:kotlin-reflect") // Reflection
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Kotlin Standard Library

	// Jackson JSR310 (for Java 8 Date/Time)
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")

	// Test Dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Testing
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
