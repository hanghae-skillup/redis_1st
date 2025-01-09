dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

springBoot {
    mainClass.set("kr.spartacodingclub.skillup.Redis1stApplicationKt")
}