# 용량 시험
- 이 page는 본 app의 redis 추가 전 후의 용량을 비교한 문서이다.
- k6로 용량 측정함

## Test Case
- title 조회 API를 이용하여 용량을 측정
- 5분 * 10명 * 100개 title을 조회
- 사용한 k6스크립트
```ts
import http from 'k6/http';

import {sleep} from "k6"

export let options = {
    vus: 10,
    duration: '5m'
}

const BASE_URL = 'http://localhost:8080/api/search?title=title'

export default function() {
    
    let getUrl = BASE_URL
    for (let titleid=1; titleid<=100; titleid++) {
        http.get(getUrl+titleid);
        // console.log(getUrl+titleid);
    }
    
}
``` 


## redis 도입 이전
```
         /\      Grafana   /‾‾/
    /\  /  \     |\  __   /  /
   /  \/    \    | |/ /  /   ‾‾\
  /          \   |   (  |  (‾)  |
 / __________ \  |_|\_\  \_____/

     execution: local
        script: script.ts
        output: -

     scenarios: (100.00%) 1 scenario, 10 max VUs, 5m30s max duration (incl. graceful stop):
              * default: 10 looping VUs for 5m0s (gracefulStop: 30s)


     data_received..................: 583 MB  1.9 MB/s
     data_sent......................: 186 MB  620 kB/s
     http_req_blocked...............: avg=4µs      min=0s       med=2µs     max=5.25ms   p(90)=3µs      p(95)=4µs
     http_req_connecting............: avg=1.69µs   min=0s       med=0s      max=2.74ms   p(90)=0s       p(95)=0s
     http_req_duration..............: avg=1.64ms   min=569µs    med=1.54ms  max=108.95ms p(90)=2.14ms   p(95)=2.45ms
       { expected_response:true }...: avg=1.64ms   min=569µs    med=1.54ms  max=108.95ms p(90)=2.14ms   p(95)=2.45ms
     http_req_failed................: 0.00%   0 out of 1791100
     http_req_receiving.............: avg=32.95µs  min=4µs      med=24µs    max=17.75ms  p(90)=61µs     p(95)=80µs
     http_req_sending...............: avg=5.36µs   min=1µs      med=5µs     max=8.17ms   p(90)=9µs      p(95)=11µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.6ms    min=556µs    med=1.5ms   max=107.12ms p(90)=2.1ms    p(95)=2.4ms
     http_reqs......................: 1791100 5968.084002/s
     iteration_duration.............: avg=167.52ms min=124.42ms med=161.7ms max=550.56ms p(90)=190.82ms p(95)=208.4ms
     iterations.....................: 17911   59.68084/s
     vus............................: 10      min=10           max=10
     vus_max........................: 10      min=10           max=10


running (5m00.1s), 00/10 VUs, 17911 complete and 0 interrupted iterations
default ✓ [======================================] 10 VUs  5m0s
```

## redis cache 도입 이후
```
         /\      Grafana   /‾‾/
    /\  /  \     |\  __   /  /
   /  \/    \    | |/ /  /   ‾‾\
  /          \   |   (  |  (‾)  |
 / __________ \  |_|\_\  \_____/

     execution: local
        script: script.ts
        output: -

     scenarios: (100.00%) 1 scenario, 10 max VUs, 5m30s max duration (incl. graceful stop):
              * default: 10 looping VUs for 5m0s (gracefulStop: 30s)


     data_received..................: 1.1 GB  3.7 MB/s
     data_sent......................: 350 MB  1.2 MB/s
     http_req_blocked...............: avg=2.7µs    min=0s       med=1µs     max=9.12ms   p(90)=2µs      p(95)=3µs
     http_req_connecting............: avg=1.2µs    min=0s       med=0s      max=9.08ms   p(90)=0s       p(95)=0s
     http_req_duration..............: avg=870.64µs min=369µs    med=795µs   max=50.91ms  p(90)=1.15ms   p(95)=1.32ms
       { expected_response:true }...: avg=870.64µs min=369µs    med=795µs   max=50.91ms  p(90)=1.15ms   p(95)=1.32ms
     http_req_failed................: 0.00%   0 out of 3370000
     http_req_receiving.............: avg=23.05µs  min=-39000ns med=16µs    max=27.43ms  p(90)=45µs     p(95)=59µs
     http_req_sending...............: avg=3.22µs   min=-36000ns med=2µs     max=2.45ms   p(90)=5µs      p(95)=7µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=844.36µs min=358µs    med=770µs   max=50.19ms  p(90)=1.12ms   p(95)=1.29ms
     http_reqs......................: 3370000 11232.224713/s
     iteration_duration.............: avg=89.01ms  min=67.14ms  med=85.25ms max=673.79ms p(90)=104.39ms p(95)=111.23ms
     iterations.....................: 33700   112.322247/s
     vus............................: 10      min=10           max=10
     vus_max........................: 10      min=10           max=10


running (5m00.0s), 00/10 VUs, 33700 complete and 0 interrupted iterations
default ✓ [======================================] 10 VUs  5m0s
```

## 결론
- 동일 시나리오세ㅓ redis cache 도입후 data_received 가 2배 증가
- 이전
```
data_received..................: 583 MB  1.9 MB/s
```
- 이후
```
data_received..................: 1.1 GB  3.7 MB/s
```
