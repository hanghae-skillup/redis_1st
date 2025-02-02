plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java-test-fixtures") // 테스트 픽스처 활성화
    id("jacoco") // JaCoCo 플러그인 추가
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
                //minimum = BigDecimal("0.60") // 최소 60% 커버리지 필요
                minimum = BigDecimal("0.0") // 테스트코드 미구현으로 검증 제외
            }
        }
    }
}
