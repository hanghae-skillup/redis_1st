## 프로젝트 설명
상영중인 영화 조회 및 예약 시스템 입니다. 

### 1. 기본 구성
- SpringBoot 3.3
- Java 21
- Gradle
- JPA
- MYSQL 5.7
- Docker Compose
  <br><br>
### 2. 아키텍처 설계
- 멀티 모듈 및 레이어드 아키텍처 구성
  <br>

### 3. 디렉토리 구조
hhCinema/

├── application/          
├── domain/          
├── infrastructure/       
├── presentation/               
├── init-scripts/                
├── docker-compose.yml    
├── build.gradle          
└── settings.gradle     

  <br>
  
### 4. ER 다이어그림

![hhCinema_ERD](https://github.com/user-attachments/assets/8f50b65c-5b75-4b6c-9889-1e85b1c9aa15)


### 5. 데이터 케시
- Redis 사용하여 영화 상영 리스트를 Cache에 저장.

#### 부하 테스트

#### 캐시 적용 전과 캐시 적용 후 비교

- 캐시 적용전

```
     data_received..................: 889 kB 29 kB/s
     data_sent......................: 31 kB  1.0 kB/s
     http_req_blocked...............: avg=219.54µs min=1.9µs   med=4.92µs   max=9.36ms   p(90)=8.24µs  p(95)=14.6µs
     http_req_connecting............: avg=126.93µs min=0s      med=0s       max=6.65ms   p(90)=0s      p(95)=0s
     http_req_duration..............: avg=22.96ms  min=9.99ms  med=20.92ms  max=56.3ms   p(90)=31.11ms p(95)=35.63ms
       { expected_response:true }...: avg=22.96ms  min=9.99ms  med=20.92ms  max=56.3ms   p(90)=31.11ms p(95)=35.63ms
     http_req_failed................: 0.00%  0 out of 300
     http_req_receiving.............: avg=685.61µs min=33.23µs med=486.81µs max=7.21ms   p(90)=1.29ms  p(95)=1.88ms
     http_req_sending...............: avg=20.28µs  min=4.43µs  med=12.9µs   max=175.63µs p(90)=34.1µs  p(95)=67.14µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=22.26ms  min=9.58ms  med=20.46ms  max=55.64ms  p(90)=30.28ms p(95)=35.07ms
     http_reqs......................: 300    9.763772/s
     iteration_duration.............: avg=1.02s    min=1.01s   med=1.02s    max=1.06s    p(90)=1.03s   p(95)=1.03s
     iterations.....................: 300    9.763772/s
     vus............................: 10     min=10       max=10
     vus_max........................: 10     min=10       max=10


running (0m30.7s), 00/10 VUs, 300 complete and 0 interrupted iterations
default ✓ [ 100% ] 10 VUs  30s
```

- caffeine 캐시 적용후

```
     data_received..................: 889 kB 29 kB/s
     data_sent......................: 31 kB  1.0 kB/s
     http_req_blocked...............: avg=203.2µs  min=1.87µs med=5.08µs   max=7.43ms   p(90)=8.96µs  p(95)=15.89µs
     http_req_connecting............: avg=118.53µs min=0s     med=0s       max=5.05ms   p(90)=0s      p(95)=0s
     http_req_duration..............: avg=7.21ms   min=2.47ms med=4.73ms   max=83.38ms  p(90)=5.91ms  p(95)=6.81ms
       { expected_response:true }...: avg=7.21ms   min=2.47ms med=4.73ms   max=83.38ms  p(90)=5.91ms  p(95)=6.81ms
     http_req_failed................: 0.00%  0 out of 300
     http_req_receiving.............: avg=738.65µs min=38.4µs med=630.58µs max=3.1ms    p(90)=1.42ms  p(95)=1.57ms
     http_req_sending...............: avg=22.5µs   min=4.75µs med=13.43µs  max=557.16µs p(90)=32.18µs p(95)=63.81µs
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=6.45ms   min=2.18ms med=3.95ms   max=82.66ms  p(90)=5.28ms  p(95)=5.54ms
     http_reqs......................: 300    9.919515/s
     iteration_duration.............: avg=1s       min=1s     med=1s       max=1.08s    p(90)=1s      p(95)=1s
     iterations.....................: 300    9.919515/s
     vus............................: 10     min=10       max=10
     vus_max........................: 10     min=10       max=10


running (0m30.2s), 00/10 VUs, 300 complete and 0 interrupted iterations
default ✓ [ 100% ] 10 VUs  30s

```

| **Metric**              | **캐시 적용 전** | **캐시적용 후** | **차이**              |
|--------------------------|-------------|-------------|-----------------------|
| 평균 대기 시간 (avg)     | 22.26ms     | 6.45ms      | 캐시적용 후가 더 빠름     |
| 최소 대기 시간 (min)     | 9.58ms      | 2.18ms      | 캐시적용 후가 더 빠름     |
| 최대 대기 시간 (max)     | 55.64ms     | 82.66ms     | 캐시적용 전이 더 낮음     |
| P(90) 대기 시간         | 30.28ms     | 5.28ms      | 캐시적용 후가 더 빠름     |
| P(95) 대기 시간         | 35.07ms     | 5.54ms      | 캐시적용 후가 더 빠름     |

#### caffeine 캐시와 redis cache 비교

- caffeine 캐시 테스트
```
     data_received..................: 200 MB 6.1 MB/s
     data_sent......................: 6.4 MB 194 kB/s
     http_req_blocked...............: avg=522.73ms min=0s      med=6.7µs   max=16.66s   p(90)=620.65ms p(95)=3.16s
     http_req_connecting............: avg=519.43ms min=0s      med=0s      max=16.3s    p(90)=572.52ms p(95)=3.16s
     http_req_duration..............: avg=2.14s    min=0s      med=2.02s   max=11.53s   p(90)=3.51s    p(95)=4.23s
       { expected_response:true }...: avg=2.23s    min=37.59ms med=2.04s   max=11.53s   p(90)=3.6s     p(95)=4.35s
     http_req_failed................: 3.84%  2474 out of 64344
     http_req_receiving.............: avg=81.24ms  min=0s      med=44.07ms max=3.67s    p(90)=167.53ms p(95)=244.21ms
     http_req_sending...............: avg=725.22µs min=0s      med=16.6µs  max=270.13ms p(90)=54.01µs  p(95)=121.21µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.06s    min=0s      med=1.96s   max=11.47s   p(90)=3.29s    p(95)=3.89s
     http_reqs......................: 64344  1958.318807/s
     iteration_duration.............: avg=4.81s    min=1.03s   med=3.06s   max=31.83s   p(90)=6.47s    p(95)=22.38s
     iterations.....................: 64344  1958.318807/s
     vus............................: 1520   min=1520          max=10000
     vus_max........................: 10000  min=10000         max=10000


running (0m32.9s), 00000/10000 VUs, 64344 complete and 0 interrupted iterations
default ✓ [ 100% ] 10000 VUs  30s

```

- Redis 캐시 테스트 

```
     data_received..................: 154 MB 4.6 MB/s
     data_sent......................: 6.0 MB 180 kB/s
     http_req_blocked...............: avg=595.67ms min=0s       med=6.75µs  max=17.44s  p(90)=912.48ms p(95)=3.78s
     http_req_connecting............: avg=559.43ms min=0s       med=0s      max=16.82s  p(90)=632.1ms  p(95)=3.65s
     http_req_duration..............: avg=3.08s    min=0s       med=2.94s   max=9.98s   p(90)=4.99s    p(95)=5.67s
       { expected_response:true }...: avg=3.27s    min=833.17ms med=3.01s   max=9.98s   p(90)=5.11s    p(95)=5.7s
     http_req_failed................: 5.80%  2939 out of 50586
     http_req_receiving.............: avg=103.59ms min=0s       med=38.48ms max=4.25s   p(90)=316.9ms  p(95)=366.1ms
     http_req_sending...............: avg=412.53µs min=0s       med=17.02µs max=70.27ms p(90)=73.38µs  p(95)=254.39µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.98s    min=0s       med=2.87s   max=9.76s   p(90)=4.71s    p(95)=5.5s
     http_reqs......................: 50586  1516.585156/s
     iteration_duration.............: avg=6.18s    min=1.15s    med=4.06s   max=31.73s  p(90)=9.22s    p(95)=21.54s
     iterations.....................: 50586  1516.585156/s
     vus............................: 1879   min=1879          max=10000
     vus_max........................: 10000  min=10000         max=10000


running (0m33.4s), 00000/10000 VUs, 50586 complete and 0 interrupted iterations
default ✓ [ 100% ] 10000 VUs  30s
```

| **Metric**                | **Caffeine 캐시**         | **Redis 캐시**           | **차이**                           |
|---------------------------|--------------------------|--------------------------|------------------------------------|
| **data_received**         | 200 MB (6.1 MB/s)       | 154 MB (4.6 MB/s)        | Caffeine 캐시가 더 많이 수신      |
| **data_sent**             | 6.4 MB (194 kB/s)       | 6.0 MB (180 kB/s)        | Caffeine 캐시가 더 많이 전송      |
| **http_req_blocked (avg)**| 522.73ms                | 595.67ms                 | Caffeine 캐시가 더 빠름           |
| **http_req_blocked (p90)**| 620.65ms                | 912.48ms                 | Caffeine 캐시가 더 빠름           |
| **http_req_blocked (p95)**| 3.16s                   | 3.78s                    | Caffeine 캐시가 더 빠름           |
| **http_req_connecting**   | avg=519.43ms, max=16.3s | avg=559.43ms, max=16.82s | Caffeine 캐시가 더 빠름           |
| **http_req_duration (avg)**| 2.14s                  | 3.08s                    | Caffeine 캐시가 더 빠름           |
| **http_req_duration (p90)**| 3.51s                  | 4.99s                    | Caffeine 캐시가 더 빠름           |
| **http_req_duration (p95)**| 4.23s                  | 5.67s                    | Caffeine 캐시가 더 빠름           |
| **http_req_failed**       | 3.84% (2474/64344)      | 5.80% (2939/50586)       | Caffeine 캐시가 더 적은 실패율    |
| **http_req_receiving (avg)**| 81.24ms               | 103.59ms                 | Caffeine 캐시가 더 빠름           |
| **http_req_sending (avg)**| 412.53µs               | 412.53µs                 | 동일                             |
| **http_req_waiting (avg)**| 2.06s                  | 2.98s                    | Caffeine 캐시가 더 빠름           |
| **http_reqs**             | 64344 (1958.32/s)      | 50586 (1516.59/s)        | Caffeine 캐시가 처리량 더 높음    |
| **iteration_duration (avg)**| 4.81s                 | 6.18s                    | Caffeine 캐시가 더 빠름           |
| **iterations**            | 64344                  | 50586                    | Caffeine 캐시가 더 많은 요청 처리 |
