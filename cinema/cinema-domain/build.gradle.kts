apply {
    plugin("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    implementation("net.datafaker:datafaker:2.4.2")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    kapt(group ="com.querydsl", name = "querydsl-apt", classifier = "jakarta")

}
