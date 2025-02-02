dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.32")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.mockk:mockk:1.13.5")

    implementation(project(":module-common"))
    implementation(project(":module-domain"))
    implementation(project(":module-application"))
}

//// Kotlin QClass Setting
//kotlin.sourceSets.main {
//    println("kotlin sourceSets builDir:: $buildDir")
//    setBuildDir("$buildDir")
//}

idea {
    module { val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain) }
}
