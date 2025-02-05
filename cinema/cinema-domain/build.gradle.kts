apply {
    plugin("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    kapt(group ="com.querydsl", name = "querydsl-apt", classifier = "jakarta")

}
