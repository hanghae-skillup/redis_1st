plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.hanghae"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")

	dependencies {
		implementation("jakarta.transaction:jakarta.transaction-api")
		implementation("org.springframework.boot:spring-boot-starter")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		annotationProcessor("org.projectlombok:lombok")
		compileOnly("org.projectlombok:lombok")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
