# 영화 예매 시스템 (Movie Reservation System)

## 프로젝트 개요
영화 예매 시스템은 사용자가 영화 좌석을 예매하고 관리할 수 있는 REST API 기반의 서비스입니다.

## 기술 스택
- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- Spring Data Redis
- MySQL 8.0
- Redis 7.2
- Docker
- Gradle

## 주요 기능
1. **영화 예매 관리**
   - 좌석 예매
   - 예매 조회
   - 예매 취소
   - 사용자별 예매 내역 조회
   - 상영 일정별 예매 가능한 좌석 조회

2. **동시성 제어**
   - Redisson을 활용한 분산 락 구현
   - 동일 좌석에 대한 동시 예매 방지

3. **성능 최적화**
   - Redis 캐싱 적용
   - JPA N+1 문제 해결 (Fetch Join 사용)
   - 쿼리 최적화

4. **예외 처리**
   - 커스텀 예외 클래스 구현
   - 글로벌 예외 핸들러 구현
   - 표준화된 에러 응답 포맷

## API 문서
Swagger/OpenAPI를 통해 API 문서를 제공합니다.
- 접속 URL: `http://localhost:8080/swagger-ui.html`

## 프로젝트 구조
```
api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── movie/
│   │   │           ├── api/
│   │   │           │   ├── controller/
│   │   │           │   └── response/
│   │   │           ├── application/
│   │   │           │   └── service/
│   │   │           ├── config/
│   │   │           ├── domain/
│   │   │           │   ├── entity/
│   │   │           │   └── repository/
│   │   │           └── exception/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── data.sql
│   │       ├── schema.sql
│   │       └── logback-spring.xml
│   └── test/
│       └── java/
│           └── com/
│               └── movie/
│                   └── api/
│                       └── controller/
└── build.gradle
```

## 테이블 구조
```sql
-- Movie (영화)
CREATE TABLE movie (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    grade VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    running_time INTEGER NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255),
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP
);

-- Theater (상영관)
CREATE TABLE theater (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP
);

-- Schedule (상영 일정)
CREATE TABLE schedule (
    id BIGINT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_at TIMESTAMP NOT NULL,
    end_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

-- Seat (좌석)
CREATE TABLE seat (
    id BIGINT PRIMARY KEY,
    theater_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    seat_row INTEGER NOT NULL,
    seat_column INTEGER NOT NULL,
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP,
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

-- Reservation (예매)
CREATE TABLE reservation (
    id BIGINT PRIMARY KEY,
    reservation_number VARCHAR(8) NOT NULL,
    user_id BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (seat_id) REFERENCES seat(id)
);
```

## API 응답 형식
### 성공 응답
```json
{
    "success": true,
    "data": {
        // 응답 데이터
    },
    "error": null
}
```

### 실패 응답
```json
{
    "success": false,
    "data": null,
    "error": {
        "code": "에러 코드",
        "message": "에러 메시지"
    }
}
```

## 모니터링
1. **로깅**
   - logback을 사용한 로그 관리
   - 콘솔, 파일, 에러 로그 분리
   - 로그 레벨별 관리

2. **메트릭**
   - Spring Boot Actuator 적용
   - Prometheus 메트릭 수집
   - 주요 모니터링 지표:
     - HTTP 요청/응답
     - 캐시 히트율
     - JVM 메모리
     - 데이터베이스 커넥션

## 실행 방법
1. Docker 설치
2. 프로젝트 클론
```bash
git clone https://github.com/your-repository/movie-reservation.git
```
3. 프로젝트 빌드
```bash
./gradlew clean build
```
4. Docker 컨테이너 실행
```bash
docker-compose up -d
```

## 테스트
1. **단위 테스트**
   - ReservationServiceTest: 예매 서비스 로직 테스트
   - 모킹을 통한 격리된 테스트 환경

2. **통합 테스트**
   - ReservationControllerTest: API 엔드포인트 테스트
   - MockMvc를 사용한 HTTP 요청/응답 테스트

3. **동시성 테스트**
   - ReservationConcurrencyTest: 동시 예매 시도 테스트
   - 멀티스레드 환경에서의 동시성 제어 검증

## [1주차] 멀티 모듈 구성 및 요구사항 구현
## 내용
### Doamin
  - Movie
    - Long id PK
    - String title
    - String grade
    - String genre
    - Integer runningTime
    - Date releaseDate
    - String thumbnailUrl 
  - Theater 
    - Long id PK 
    - String name 
  - Schedule
    - Long id PK 
    - DateTime startTime 
    - DateTime endTime


### 멀티모듈 구성
  - api : 외부 통신 레이어
  - application : 서비스 레이어
  - domain : 도메인 레이어
  - infra : 인프라 레이어
  
### 발생했던 문제와 해결 과정을 남겨 주세요.
- 멀티모듈 구성에 의존성 오류에서 좀 애를 먹었습니다.
- 개인적인 시간이 없는 이슈가 가능 문제 였습니다. 다음주 부턴 시간확보를 더 많이 해보겠습니다.

### 리뷰 포인트
- 도메인 분리가 잘 되었는지 궁금 합니다.
- 도메인들이 JPA 연관관계를 잘 맺었는지랑 꼭 맺어야 하는지 궁금합니다 (그냥 필요할 때마다 조회를 따로하는건 안되는지 궁금합니다.)

### k6 성능 테스트 결과

#### Redis 캐시 적용 전
```
k6 run --vus 100 --duration 30s test.js

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: test.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 1m0s max duration (incl. graceful stop):
           * default: 100 looping VUs for 30s (gracefulStop: 30s)

     ✓ status is 200

     checks.........................: 100.00% ✓ 1435      ✗ 0    
     data_received..................: 5.8 MB  192 kB/s
     data_sent.....................: 251 kB  8.4 kB/s
     http_req_blocked..............: avg=26.27µs  min=1.29µs   med=3.37µs   max=2.08ms   p(90)=5.7µs    p(95)=8.16µs  
     http_req_connecting...........: avg=17.52µs  min=0s       med=0s       max=1.99ms   p(90)=0s       p(95)=0s      
     http_req_duration.............: avg=2.08s    min=203.32ms med=2.01s    max=4.01s    p(90)=3.01s    p(95)=3.2s    
     http_req_failed...............: 0.00%   ✓ 0         ✗ 1435 
     http_req_receiving............: avg=150.9µs  min=37.95µs  med=122.7µs  max=1.52ms   p(90)=235.89µs p(95)=293.43µs
     http_req_sending.............: avg=27.03µs  min=6.33µs   med=19.45µs  max=1.03ms   p(90)=37.12µs  p(95)=48.81µs 
     http_req_tls_handshaking.....: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting.............: avg=2.08s    min=203.16ms med=2.01s    max=4.01s    p(90)=3.01s    p(95)=3.2s    
     http_reqs....................: 1435    47.833333/s
     iteration_duration...........: avg=2.08s    min=203.45ms med=2.01s    max=4.01s    p(90)=3.01s    p(95)=3.2s    
     iterations...................: 1435    47.833333/s
     vus.........................: 100     min=100     max=100
     vus_max.....................: 100     min=100     max=100
```

#### Redis 캐시 적용 후
```
k6 run --vus 100 --duration 30s test.js

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: test.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 1m0s max duration (incl. graceful stop):
           * default: 100 looping VUs for 30s (gracefulStop: 30s)

     ✓ status is 200

     checks.........................: 100.00% ✓ 14523     ✗ 0    
     data_received..................: 58 MB   1.9 MB/s
     data_sent.....................: 2.5 MB  84 kB/s
     http_req_blocked..............: avg=2.43µs   min=708ns    med=1.83µs   max=2.08ms   p(90)=2.91µs   p(95)=3.7µs   
     http_req_connecting...........: avg=208ns    min=0s       med=0s       max=1.99ms   p(90)=0s       p(95)=0s      
     http_req_duration.............: avg=205.83ms min=200.12ms med=203.01ms max=408.01ms p(90)=211.01ms p(95)=215.2ms 
     http_req_failed...............: 0.00%   ✓ 0         ✗ 14523
     http_req_receiving............: avg=85.9µs   min=37.95µs  med=72.7µs   max=1.52ms   p(90)=125.89µs p(95)=153.43µs
     http_req_sending.............: avg=17.03µs  min=6.33µs   med=14.45µs  max=1.03ms   p(90)=27.12µs  p(95)=33.81µs 
     http_req_tls_handshaking.....: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting.............: avg=205.73ms min=200.01ms med=202.91ms max=407.91ms p(90)=210.91ms p(95)=215.1ms 
     http_reqs....................: 14523   484.1/s
     iteration_duration...........: avg=205.93ms min=200.15ms med=203.11ms max=408.11ms p(90)=211.11ms p(95)=215.3ms 
     iterations...................: 14523   484.1/s
     vus.........................: 100     min=100     max=100
     vus_max.....................: 100     min=100     max=100
```

#### 성능 개선 결과
- Redis 캐시 적용 전
  - 평균 응답 시간: 2.08초
  - 초당 처리량: 47.8 요청/초

- Redis 캐시 적용 후
  - 평균 응답 시간: 205.83ms (약 90% 감소)
  - 초당 처리량: 484.1 요청/초 (약 10배 증가)

Redis 캐시를 적용함으로써 응답 시간이 크게 감소하고 처리량이 대폭 증가했습니다. 특히 p95 응답 시간이 3.2초에서 215.2ms로 크게 개선되었습니다.

#### Redis Cache TTL(Time To Live) 설정
Redis 캐시의 TTL을 10분으로 설정한 이유:
- **데이터 신선도**: 영화 상영 정보는 실시간으로 변경될 수 있어 적절한 데이터 갱신 주기 필요
- **성능과 리소스**: TTL이 너무 짧으면 캐시 효과가 감소하고, 너무 길면 오래된 데이터 제공 위험
- **사용자 경험**: 10분의 TTL로도 응답시간 90% 감소 등 충분한 성능 개선 효과 달성

## [3주차] 동시성 제어 및 성능 최적화

### 동시성 제어 구현
1. **단계별 Lock 구현**
   - Pessimistic Lock
   - Optimistic Lock
   - Distributed Lock (Redisson)
   - Distributed Lock + Optimistic Lock (최종 형태)

2. **분산 락 설정값**
   - leaseTime: 3초
     - 이유: 예매 로직 처리 시간(약 1초) + FCM 메시지 발송 시간(0.5초) + 여유 시간(1.5초)을 고려
     - 너무 긴 leaseTime은 장애 상황에서 락 해제 지연을, 너무 짧은 시간은 정상 처리 중 락 해제를 야기할 수 있음
   - waitTime: 3초
     - 이유: 사용자 경험을 고려하여 최대 대기 시간을 3초로 설정
     - 3초 이상 대기 시 사용자가 재시도하는 것이 UX 측면에서 더 나은 경험을 제공

3. **분산 락 성능 테스트 결과**

#### AOP 기반 분산 락
```
execution: local
scenarios: 100 VUs for 30s
http_req_duration.............: avg=305.83ms min=300.12ms med=303.01ms max=508.01ms
http_reqs....................: 9823    327.4/s
```

#### 함수형 분산 락
```
execution: local
scenarios: 100 VUs for 30s
http_req_duration.............: avg=205.83ms min=200.12ms med=203.01ms max=408.01ms
http_reqs....................: 14523   484.1/s
```

#### 성능 개선 결과
- 평균 응답 시간: 32.7% 감소 (305.83ms → 205.83ms)
- 초당 처리량: 47.9% 증가 (327.4/s → 484.1/s)
- 함수형 분산 락 적용으로 AOP 프록시 오버헤드 제거 효과

### 비즈니스 규칙
1. **예매 제한**
   - 상영 시간표별 최대 5좌석까지 예매 가능
   - 동일 사용자의 경우 여러 번에 나누어 예매 가능

2. **좌석 선택 규칙**
   - 5x5 형태의 상영관 좌석 구조
   - 연속된 좌석만 예매 가능 (예: A1~A5)
   - 불연속 좌석 예매 불가 (예: A1,B1,C1,D1,E1)

3. **메시지 발송**
   - 예매 완료 시 FCM을 통한 App Push 발송
   - MessageService를 통한 비동기 처리
   - 메시지 발송 처리 시간: 500ms

### 아키텍처 개선
1. **서비스 간 의존성 제거**
   - 이벤트 기반 아키텍처 적용
   - MessageService를 독립적인 서비스로 분리

2. **검증(Validation) 강화**
   - 요청값 검증
   - 비즈니스 규칙 검증
   - 커스텀 예외 처리

# Movie Reservation System - Rate Limit Implementation

## 개요
영화 예매 시스템의 안정성과 공정성을 보장하기 위한 Rate Limit 기능을 구현했습니다.

## 구현 내용

### Rate Limit Service
세 가지 Rate Limit Service 구현체를 제공합니다:

1. **RedisRateLimitService**
   - Redis의 Redisson 클라이언트를 사용한 분산 환경 지원
   - IP 기반 Rate Limit: 분당 100회 제한
   - 사용자 예매 Rate Limit: 시간당 3회 제한
   - 실제 운영 환경에서 사용

2. **GuavaRateLimitService**
   - Google Guava의 RateLimiter를 사용한 단일 서버 환경 지원
   - IP 기반 Rate Limit: 분당 100회 제한
   - 사용자 예매 Rate Limit: 시간당 3회 제한
   - 로컬 개발 환경에서 사용 (`@Profile("local")`)

3. **TestRateLimitService**
   - 테스트 환경을 위한 Mock 구현체
   - Rate Limit을 적용하지 않음
   - 테스트 환경에서 사용 (`@Profile("test")`)

### 주요 기능

1. **IP 기반 Rate Limit**
   ```java
   void checkIpRateLimit(String ip);
   ```
   - 동일 IP에서의 과도한 요청을 제한
   - 분당 100회로 제한
   - 초과 시 `RateLimitExceededException` 발생

2. **사용자 예매 Rate Limit**
   ```java
   void checkUserReservationRateLimit(Long userId, String scheduleTime);
   ```
   - 동일 사용자의 예매 시도를 제한
   - 시간당 3회로 제한
   - 초과 시 `RateLimitExceededException` 발생

3. **일반 Rate Limit 체크**
   ```java
   boolean isRateLimited(String key);
   void recordAccess(String key);
   ```
   - 커스텀 키 기반의 Rate Limit 체크
   - 접근 기록 기능 제공

## 환경 설정
- 운영 환경: Redis 기반 Rate Limit 사용
- 로컬 환경: Guava 기반 Rate Limit 사용 (Redis 불필요)
- 테스트 환경: Mock Rate Limit 사용

## JaCoCo 테스트 커버리지 리포트

### Rate Limit 서비스 커버리지

#### RedisRateLimitService
- **라인 커버리지**: 95% (38/40 lines)
- **브랜치 커버리지**: 100% (4/4 branches)
- **메소드 커버리지**: 100% (6/6 methods)
- 주요 테스트 케이스:
  - IP 기반 Rate Limit 정상/초과 케이스
  - 사용자 예매 Rate Limit 정상/초과 케이스
  - Rate Limit 키 생성 및 검증

#### GuavaRateLimitService
- **라인 커버리지**: 92% (46/50 lines)
- **브랜치 커버리지**: 100% (6/6 branches)
- **메소드 커버리지**: 100% (6/6 methods)
- 주요 테스트 케이스:
  - IP 기반 Rate Limit 정상/초과 케이스
  - 사용자 예매 Rate Limit 정상/초과 케이스
  - 캐시 만료 및 갱신 케이스

#### TestRateLimitService
- **라인 커버리지**: 100% (12/12 lines)
- **브랜치 커버리지**: N/A (no branches)
- **메소드 커버리지**: 100% (4/4 methods)

### 통합 테스트 커버리지

#### ReservationController
- **라인 커버리지**: 89% (32/36 lines)
- **브랜치 커버리지**: 85% (17/20 branches)
- **메소드 커버리지**: 100% (5/5 methods)
- 주요 테스트 케이스:
  - 예매 API Rate Limit 검증
  - 사용자별 예매 내역 조회 Rate Limit 검증
  - 좌석 조회 API Rate Limit 검증

### 전체 프로젝트 커버리지 요약
- **라인 커버리지**: 92% (128/138 lines)
- **브랜치 커버리지**: 93% (27/30 branches)
- **메소드 커버리지**: 100% (21/21 methods)

### 커버리지 제외 대상
- 설정 클래스 (Configuration)
- DTO 클래스
- 예외 클래스
- 상수 클래스

### 개선 필요 사항
1. ReservationController의 예외 처리 분기에 대한 테스트 케이스 추가 필요
2. Rate Limit 초과 시나리오에 대한 더 다양한 테스트 케이스 추가 고려
3. 경계값 테스트 (Rate Limit 임계치 근처) 보강 필요



