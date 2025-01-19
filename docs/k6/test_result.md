# K6 테스트 결과
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
![k6_test1.png](..%2Fimg%2Fk6_test1.png)  
  
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
![k6_test2.png](..%2Fimg%2Fk6_test2.png)  
  
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
![k6_test3.png](..%2Fimg%2Fk6_test3.png)  