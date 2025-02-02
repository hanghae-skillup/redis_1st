plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java-test-fixtures") // 테스트 픽스처 활성화
    id("jacoco") // JaCoCo 플러그인 추가
    //id("jacoco-report-aggregation") // JaCoCo 멀티모듈 리포트 통합하기 위한 플러그인
}

group = "com.hanghae"
version = "0.0.1-SNAPSHOT"

jacoco {
    toolVersion = "0.8.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":cinema-application")) // 애플리케이션 계층 의존성
    implementation(project(":cinema-infrastructure")) // RepositoryPort 구현체를 찾지 못해서 추가
    implementation("org.springframework.boot:spring-boot-starter-web") // web
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") //jpa 레포지토리 의존성 문제로 추가 (@EnableJpaRepositories)
    testFixturesImplementation(project(":cinema-domain")) // testFixtures에서 도메인 객체 사용 위함
    testFixturesImplementation(project(":cinema-application")) // testFixtures에서 application 객체 사용 위함
    // testFixturesImplementation로 의존성 추가한 계층은 /src/testFixtures 하위 경로에서만 사용 가능 하다.
    // 양쪽 계층에 테스트 픽스처 활성화 설정(id("java-test-fixtures"))이 되어 있어야 한다.

    testImplementation("org.springframework.boot:spring-boot-starter-data-redis") // test 코드에서 RedisTemplate 사용 위함

    testImplementation ("org.testcontainers:testcontainers:1.19.3") //테스트 컨테이너
    testImplementation ("org.testcontainers:junit-jupiter:1.19.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // test 실행 후 리포트 생성

    reports {
        xml.required.set(false)    // XML 리포트 비활성화
        csv.required.set(false)    // csv 리포트 비활성화
        html.required.set(true)   // HTML 리포트 활성화
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco")) //저장경로 설정
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)

    violationRules {
        rule {
            element = "CLASS"
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = BigDecimal("0.60") // 최소 60% 커버리지 필요
                excludes = listOf("com.hanghae.adapter.web.MovieController") // 테스트코드 미구현으로 검증 제외
            }
        }
    }
}
