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



