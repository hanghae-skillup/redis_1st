plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

springBoot {
    mainClass.set("com.example.movie.MovieApplicationKt")
}

dependencies {
    implementation(project(":module-common"))
    implementation(project(":module-external"))

    // Web 관련 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
}