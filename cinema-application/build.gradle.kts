plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java-test-fixtures") // 테스트 픽스처 활성화
}

group = "com.hanghae"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":cinema-domain")) // 도메인 계층 의존성
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.0") // Java, JSON 변환 라이브러리
    testFixturesImplementation(project(":cinema-domain")) // testFixtures에서 도메인 객체 사용 위함
    // testFixturesImplementation로 의존성 추가한 계층은 /src/testFixtures 하위 경로에서만 사용 가능 하다.
    // 양쪽 계층에 테스트 픽스처 활성화 설정(id("java-test-fixtures"))이 되어 있어야 한다.
}

tasks.test {
    useJUnitPlatform()
}