plugins {
    kotlin("kapt")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // MapStruct
    implementation("org.mapstruct:mapstruct:${rootProject.extra["mapstructVersion"]}")
    kapt("org.mapstruct:mapstruct-processor:${rootProject.extra["mapstructVersion"]}")
}

springBoot {
    mainClass.set("kr.spartacodingclub.cinema.CinemaApplicationKt")
}