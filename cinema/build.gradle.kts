plugins {
    kotlin("kapt") version "2.1.0"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "com.study"
version = "0.0.1-SNAPSHOT"



allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }
    dependencyManagement {
        imports {
            mavenBom("com.querydsl:querydsl-bom:5.1.0")
        }
    }

        dependencies {
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            developmentOnly("org.springframework.boot:spring-boot-docker-compose")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        }

        kotlin {
            compilerOptions {
                freeCompilerArgs.addAll("-Xjsr305=strict")
            }
        }

        java {
            toolchain {
                languageVersion = JavaLanguageVersion.of(21)
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
    }