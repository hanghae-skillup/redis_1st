# hanghae-cinema

> spring boot 3.4.1  
> java 21  
> Mysql  
> Redis  
  
### 규칙
* `infrastruct` 계층에서의 결과값은 `domain model` 혹은 `Projection(필요한 속성만 조회)` 객체 로 리턴한다.  
* `domain model`에서 `Dto`로 변환은 `application` 계층에서 한다.  
* `Dto`는 상태가 변하지 않는 경우 `recode`로 작성한다.(`RequestDto`는 거의 대부분)  
* `QueryDSL`로 조회한 결과를 받기는 객체는 `class`로 작성한다.  
* `Dto`, `Projection`는 `application`계층에 위치한다.    
* `Projection`은 `Dto`로 변환될 수 있다.    
    
---
### 캐시한 데이터
* 영화별 상영시간표 조회 결과에 대한 `Projections`를 캐시데이터로 저장하였습니다.  
---
### K6 성능 테스트 보고서
> [테이블 생성 DDL 파일 경로](docs/sql/cineam_create.sql) : `docs/sql/cineam_create.sql`
  
  
### 테스트 전제 조건
- DAU: N명
- 1명당 1일 평균 접속 수: 2번
- 피크 시간대의 집중률: 평소 트래픽의 10배
- Throughput 계산:
    - 1일 총 접속 수 = DAU × 1명당 1일 평균 접속 수 = N × 2 = 2N (1일 총 접속 수)
    - 1일 평균 RPS = 1일 총 접속 수 ÷ 86,400 (초/일)= 2N ÷ 86,400 ≈ X RPS
    - 1일 최대 RPS = 1일 평균 RPS × (최대 트래픽 / 평소 트래픽)= X × 10 = 10X RPS
- VU: N명
- optional
    - thresholds
        - e.g p(95) 의 응답 소요 시간 200ms 이하
        - 실패율 1% 이하

### 실행쿼리
```
    select
        me1_0.movie_id,
        me1_0.title,
        me1_0.rating,
        me1_0.release_date,
        concat(concat(ufe1_0.file_path, '/'), ufe1_0.file_name),
        me1_0.running_time_minutes,
        me1_0.genre,
        se1_0.screen_name,
        sse1_0.show_start_at 
    from
        movie me1_0 
    join
        screening_schedule sse1_0 
            on sse1_0.movie_id=me1_0.movie_id 
    join
        screen se1_0 
            on se1_0.screen_id=sse1_0.screen_id 
    left join
        upload_file ufe1_0 
            on ufe1_0.file_id=me1_0.file_id 
    where
        sse1_0.show_start_at>'2025-01-19 00:00:00'  
        and me1_0.title like 'S%' 
        and me1_0.genre='F' 
    order by
        me1_0.release_date desc,
        sse1_0.show_start_at;
```

### 인덱스 미적용❌, 레디스 미적용❌
🥝 SQL 실행 계획
<table border=1>
<tr>
<td bgcolor=silver class='medium'>id</td>
<td bgcolor=silver class='medium'>select_type</td>
<td bgcolor=silver class='medium'>table</td>
<td bgcolor=silver class='medium'>partitions</td>
<td bgcolor=silver class='medium'>type</td>
<td bgcolor=silver class='medium'>possible_keys</td>
<td bgcolor=silver class='medium'>key</td>
<td bgcolor=silver class='medium'>key_len</td>
<td bgcolor=silver class='medium'>ref</td>
<td bgcolor=silver class='medium'>rows</td>
<td bgcolor=silver class='medium'>filtered</td>
<td bgcolor=silver class='medium'>Extra</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>me1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ALL</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>9964</td>
<td class='normal' valign='top'>1.11</td>
<td class='normal' valign='top'>Using where; Using temporary; Using filesort</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>ufe1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ALL</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>Using where; Using join buffer (hash join)</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>sse1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ref</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u,FKpau3kcijg9uv6ejt57p71f7gc</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>cinema.me1_0.movie_id</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>33.33</td>
<td class='normal' valign='top'>Using where</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>se1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>eq_ref</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>8</td>
<td class='normal' valign='top'>cinema.sse1_0.screen_id</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>NULL</td>
</tr>
</table>  

🥝 실행 결과
```
     execution: local
        script: k6_test.js
        output: -

     scenarios: (100.00%) 1 scenario, 100 max VUs, 10m30s max duration (incl. graceful stop):
              * constant_load: 1.00 iterations/s for 10m0s (maxVUs: 100, gracefulStop: 30s)


     ✓ is status 200

     checks.........................: 100.00% 601 out of 601
     data_received..................: 215 MB  358 kB/s
     data_sent......................: 74 kB   123 B/s
     http_req_blocked...............: avg=501.07µs min=0s      med=340.9µs  max=4.85ms  p(90)=1.17ms  p(95)=1.28ms
     http_req_connecting............: avg=448.8µs  min=0s      med=289.39µs max=1.81ms  p(90)=1.14ms  p(95)=1.21ms
   ✓ http_req_duration..............: avg=44.1ms   min=38.44ms med=42.63ms  max=84.5ms  p(90)=49.13ms p(95)=52.97ms
       { expected_response:true }...: avg=44.1ms   min=38.44ms med=42.63ms  max=84.5ms  p(90)=49.13ms p(95)=52.97ms
   ✓ http_req_failed................: 0.00%   0 out of 601
     http_req_receiving.............: avg=4.53ms   min=2.85ms  med=4.28ms   max=15.29ms p(90)=5.25ms  p(95)=6.13ms
     http_req_sending...............: avg=84.37µs  min=0s      med=0s       max=1.36ms  p(90)=259.5µs p(95)=576.9µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=39.48ms  min=34.3ms  med=38.19ms  max=68.63ms p(90)=43.96ms p(95)=47.2ms
     http_reqs......................: 601     0.999931/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s    max=1.08s   p(90)=1.04s   p(95)=1.05s
     iterations.....................: 601     0.999931/s
     vus............................: 1       min=1          max=1
     vus_max........................: 100     min=100        max=100


running (10m01.0s), 000/100 VUs, 601 complete and 0 interrupted iterations
constant_load ✓ [======================================] 000/100 VUs  10m0s  1.00 iters/s
```   
![k6_test1.png](docs%2Fimg%2Fk6_test1.png)

### 인덱스 적용⭕, 레디스 미적용❌
🍊 적용한 인덱스 DDL
```
-- movie(영화) 테이블
CREATE INDEX idx_movie_title_genre ON movie (title, genre);
CREATE INDEX idx_movie_genre ON movie (genre);

-- screening_schedule(상영시간표) 테이블
CREATE INDEX idx_screening_schedule_show_start_at ON screening_schedule (show_start_at);
```

🥝 SQL 실행 계획 (=)
<table border=1>
<tr>
<td bgcolor=silver class='medium'>id</td>
<td bgcolor=silver class='medium'>select_type</td>
<td bgcolor=silver class='medium'>table</td>
<td bgcolor=silver class='medium'>partitions</td>
<td bgcolor=silver class='medium'>type</td>
<td bgcolor=silver class='medium'>possible_keys</td>
<td bgcolor=silver class='medium'>key</td>
<td bgcolor=silver class='medium'>key_len</td>
<td bgcolor=silver class='medium'>ref</td>
<td bgcolor=silver class='medium'>rows</td>
<td bgcolor=silver class='medium'>filtered</td>
<td bgcolor=silver class='medium'>Extra</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>me1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ref</td>
<td class='normal' valign='top'>PRIMARY,idx_movie_title_genre,idx_movie_genre</td>
<td class='normal' valign='top'>idx_movie_title_genre</td>
<td class='normal' valign='top'>2044</td>
<td class='normal' valign='top'>const,const</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>Using temporary; Using filesort</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>ufe1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>eq_ref</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>8</td>
<td class='normal' valign='top'>cinema.me1_0.file_id</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>NULL</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>sse1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ref</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u,FKpau3kcijg9uv6ejt57p71f7gc,idx_screening_schedule_show_start_at</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>cinema.me1_0.movie_id</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>50.00</td>
<td class='normal' valign='top'>Using where</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>se1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>eq_ref</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>8</td>
<td class='normal' valign='top'>cinema.sse1_0.screen_id</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>NULL</td>
</tr>
</table>  

🥝 SQL 실행 계획 (like)
<table border=1>
<tr>
<td bgcolor=silver class='medium'>id</td>
<td bgcolor=silver class='medium'>select_type</td>
<td bgcolor=silver class='medium'>table</td>
<td bgcolor=silver class='medium'>partitions</td>
<td bgcolor=silver class='medium'>type</td>
<td bgcolor=silver class='medium'>possible_keys</td>
<td bgcolor=silver class='medium'>key</td>
<td bgcolor=silver class='medium'>key_len</td>
<td bgcolor=silver class='medium'>ref</td>
<td bgcolor=silver class='medium'>rows</td>
<td bgcolor=silver class='medium'>filtered</td>
<td bgcolor=silver class='medium'>Extra</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>me1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ref</td>
<td class='normal' valign='top'>PRIMARY,idx_movie_title_genre,idx_movie_genre</td>
<td class='normal' valign='top'>idx_movie_genre</td>
<td class='normal' valign='top'>1022</td>
<td class='normal' valign='top'>const</td>
<td class='normal' valign='top'>2089</td>
<td class='normal' valign='top'>14.70</td>
<td class='normal' valign='top'>Using where; Using temporary; Using filesort</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>ufe1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ALL</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>Using where; Using join buffer (hash join)</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>sse1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>ref</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u,FKpau3kcijg9uv6ejt57p71f7gc,idx_screening_schedule_show_start_at</td>
<td class='normal' valign='top'>FKha49gu6vr2w6nnlxuwkqm2d1u</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>cinema.me1_0.movie_id</td>
<td class='normal' valign='top'>9</td>
<td class='normal' valign='top'>50.00</td>
<td class='normal' valign='top'>Using where</td>
</tr>

<tr>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>SIMPLE</td>
<td class='normal' valign='top'>se1_0</td>
<td class='normal' valign='top'>NULL</td>
<td class='normal' valign='top'>eq_ref</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>PRIMARY</td>
<td class='normal' valign='top'>8</td>
<td class='normal' valign='top'>cinema.sse1_0.screen_id</td>
<td class='normal' valign='top'>1</td>
<td class='normal' valign='top'>100.00</td>
<td class='normal' valign='top'>NULL</td>
</tr>
</table>  

🥝 실행 결과
```
     execution: local
        script: k6_test.js
        output: -

     scenarios: (100.00%) 1 scenario, 100 max VUs, 10m30s max duration (incl. graceful stop):
              * constant_load: 1.00 iterations/s for 10m0s (maxVUs: 100, gracefulStop: 30s)


     ✓ is status 200

     checks.........................: 100.00% 601 out of 601
     data_received..................: 215 MB  358 kB/s
     data_sent......................: 74 kB   123 B/s
     http_req_blocked...............: avg=519.24µs min=0s      med=411.6µs max=3.6ms   p(90)=1.19ms   p(95)=1.29ms
     http_req_connecting............: avg=482.82µs min=0s      med=353.7µs max=2.77ms  p(90)=1.13ms   p(95)=1.24ms
   ✓ http_req_duration..............: avg=40.39ms  min=35.3ms  med=39.39ms max=64.5ms  p(90)=44.35ms  p(95)=47.15ms
       { expected_response:true }...: avg=40.39ms  min=35.3ms  med=39.39ms max=64.5ms  p(90)=44.35ms  p(95)=47.15ms
   ✓ http_req_failed................: 0.00%   0 out of 601
     http_req_receiving.............: avg=4.29ms   min=3.17ms  med=4.09ms  max=16.08ms p(90)=4.83ms   p(95)=5.66ms
     http_req_sending...............: avg=67.75µs  min=0s      med=0s      max=1.31ms  p(90)=145.19µs p(95)=541.29µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=36.03ms  min=31.52ms med=35.28ms max=56.49ms p(90)=39.5ms   p(95)=41.35ms
     http_reqs......................: 601     0.999934/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s   max=1.06s   p(90)=1.04s    p(95)=1.04s
     iterations.....................: 601     0.999934/s
     vus............................: 1       min=1          max=1
     vus_max........................: 100     min=100        max=100


running (10m01.0s), 000/100 VUs, 601 complete and 0 interrupted iterations
constant_load ✓ [======================================] 000/100 VUs  10m0s  1.00 iters/s
```
![k6_test2.png](docs%2Fimg%2Fk6_test2.png)

### 인덱스 적용⭕, 레디스 미적용⭕
🥝 실행 결과
```
     execution: local
        script: k6_test.js
        output: -

     scenarios: (100.00%) 1 scenario, 100 max VUs, 10m30s max duration (incl. graceful stop):
              * constant_load: 1.00 iterations/s for 10m0s (maxVUs: 100, gracefulStop: 30s)


     ✓ is status 200

     checks.........................: 100.00% 601 out of 601
     data_received..................: 215 MB  358 kB/s
     data_sent......................: 74 kB   123 B/s
     http_req_blocked...............: avg=550.33µs min=0s      med=527.5µs  max=4.81ms  p(90)=1.18ms  p(95)=1.27ms
     http_req_connecting............: avg=515.81µs min=0s      med=445.79µs max=1.86ms  p(90)=1.14ms  p(95)=1.25ms
   ✓ http_req_duration..............: avg=34.6ms   min=28.79ms med=32.93ms  max=52.69ms p(90)=43.06ms p(95)=44.62ms
       { expected_response:true }...: avg=34.6ms   min=28.79ms med=32.93ms  max=52.69ms p(90)=43.06ms p(95)=44.62ms
   ✓ http_req_failed................: 0.00%   0 out of 601
     http_req_receiving.............: avg=4.23ms   min=3.12ms  med=4.17ms   max=9.2ms   p(90)=5.21ms  p(95)=5.38ms
     http_req_sending...............: avg=59.81µs  min=0s      med=0s       max=1.99ms  p(90)=126µs   p(95)=512.2µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=30.3ms   min=24.78ms med=28.9ms   max=46.37ms p(90)=37.99ms p(95)=39.43ms
     http_reqs......................: 601     0.999946/s
     iteration_duration.............: avg=1.03s    min=1.03s   med=1.03s    max=1.05s   p(90)=1.04s   p(95)=1.04s
     iterations.....................: 601     0.999946/s
     vus............................: 1       min=1          max=1
     vus_max........................: 100     min=100        max=100


running (10m01.0s), 000/100 VUs, 601 complete and 0 interrupted iterations
constant_load ✓ [======================================] 000/100 VUs  10m0s  1.00 iters/s
```  
![k6_test3.png](docs%2Fimg%2Fk6_test3.png)  
  
---
  
### 기록
> **스프링부트 실행시 `AdapterApplication.java` 파일로 실행**  
  
> `application.properties`파일은 `adapter`모듈에서 설정(DB포함 전부)
    * 스프링부트를 실행시키는 `SpringApplication`가 위치한곳  
  
> `domain model`에 `setter`사용할 예정으로 빌더패턴 사용 안함.
  
> QueryDSL 사용시 Q클래스를 사용하기 위해선 `build.gradle.kts`에 `dependencies` 추가 후 `해당 모듈` build 폴더 안에 Q클래스가 생성되어 있어야 사용 가능하다.   
>> 예) `cinema-infrastructure/build/generated/sources/annotationProcessor/java/main/com/hanghae/infrastructure/entity/QBaseEntity.java`  
>   
> 클린 후 빌드를 다시 진행시 build에 Q클래스 생성을 확인 필요  
  
> `QueryDSL`을 사용할 때 `infrastructure` 계층에서 도메인모델로 반환하게 되면 데이터 매핑 작업이 발생해 복잡해 진다.
> 그렇다고 `infrastructure` 계층에 dto를 반환하기엔 일관성이 깨진다. (dto변환은 `application` 계층에서 진행)
> 그래서 `infrastructure` 계층에서 `projection` 반환 후 `application` 계층에서 dto로 변환하려고함 (근데 의존성 떄문에 `projection`를 application에 위치시킴)
  
> ⚠ Spring Boot 3.2 버전에서는 기존의 `spring.redis.host`가 `Deprecated` 되어서 쓸 수가 없다. `spring.data.redis.host`로 적어줘야 한다.


    
### 도커컴포즈  
* 도커컴포즈 설정파일
    * `docker-compose.yml`  
      * 탭: ❌, 스페이스바 2번: ⭕ 
    
* 도커컴포즈 변수 파일
    * `.env`
      * 보안을 위해 `.gitignore`에 추가해야 하지만 교육과제므로 github에 올림 
      * `.env.dev`, `.env.prod` 나누어 환경별로 다르게 적용도 가능

* 도커컴포즈 명령어
    * 실행 : $docker-compose up -d
    * 종료 : $docker-compose down
    * 실행 상태 확인 : $docker-compose ps
    * 볼륨 재거 : $docker-compose down --volumes
    * 로그 확인 : $docker-compose logs -f // `-f`옵션을 주면 실시간
    * 특정 서비스 시작 : $docker-compose start <서비스 이름>
    * 특정 서비스 종료 : $docker-compose stop <서비스 이름>
  
    
--------------------------------------------------------------
### 적용 아키텍처
> 헥사고날 아키텍처

### 모듈 구성
> cinema-adapter  
> cinema-application  
> cinema-domain  
> cinema-infrastructure

모듈은 헥사고날 아키텍처의 계층에 따라 나누었습니다.

>1. cinema-adapter  
>     * 외부로부터의 요청을 받는 역할을 합니다.
>     * 요청을 받아 `application 계층`에 입력 포트를 호출 합니다.  
>     * `Controller`가 해당 계층에 위치합니다.
>
>2. cinema-application  
>     * 입력/출력 포트에 대한 인터페이스를 정의하고 입력 포트에 비즈니스 로직을 구현 합니다.
>     * 입력 포트(`port-in`)는 `adapter 계층`에서 호출됩니다.
>     * 입력 포트(`port-in`) 인터페이스의 구현은 `application 계층`에서 합니다. 
>     * 출력 포트(`port-out`)는 인터페이스만 정의하여 외부 시스템(DB등)과의 의존성을 최소화 합니다.
>     * 출력 포트(`port-out`) 인터페이스의 구현은 `infrastructure 계층`에서 합니다.
>     * `domain service` 로직은 `application 계층`의 `port(in)`에서 호출합니다. 
>
>3. cinema-domain  
>     * 외부에 의존하지 않는 독립적이고 핵심적인 비즈니스 모델 및 서비스 로직이 위치합니다.
>     * 외부 기술에도 의존하지 않기에 순수 자바로만 작성합니다.
>     * spring프레임워크등 프레임워크에도 의존하지 않습니다.
>     * 그렇기 떄문에 트랜잭션 관리는 애플리케이션 계층에서 처리합니다.
>
>4. cinema-infrastructure  
>    * JPA 엔티티와 데이터베이스에 접근하는 계층입니다.
>    * DB외에도 API등 외부 시스템과 상호작용 합니다.
  
도메인과 jpa엔티티를 나누었고 mapper클래스로 변환하도록 하였습니다.   
  
데이터 흐름
```
컨트롤러(adapter)  →  입력포트(application-port-in)  →  출력포트(application-port-out)  →  DB접근(infrastructure-repository)  
                                  ↓                      ↳ 출력포트의 구현체는 
                     도메인모델 or Servier(domain)           infrastructure계층 Adapter에 구현 
```
   
### 테이블 디자인

아래와 같이 7개 테이블로 구성하였습니다.
> `영화`  
> `상영관`  
> `상영시간표`  
> `영화예매내역`  
> `상영관좌석`  
> `회원`  
> `업로드파일`  

* ERD
    * `docs/cinema_erd.png`
* 테이블 생성 쿼리
    * `docs/sql/cineam_create.sql`
* 초기 데이터
    * `docs/sql/init_insert.sql`
* 더미 데이터
    * `docs/sql/dummy/movie_dummy.sql` 
    * `docs/sql/dummy/screen_dummy.sql` 
    * `docs/sql/dummy/screening_schedule_dummy.sql` 
* HTTP 테스트
    * `docs/http/cinema_test.http`   
* K6 테스트 파일
    * `docs/k6/k6_test.js` 

`영화`테이블의 `영상물등급`, `영화장르` 컬럼에 적재되는 하는 코드값은 `java`에 `enum`을 사용해 처리  
