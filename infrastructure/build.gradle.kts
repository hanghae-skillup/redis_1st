plugins {
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
}

dependencies {
    implementation("org.springframework:spring-context")
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
}
