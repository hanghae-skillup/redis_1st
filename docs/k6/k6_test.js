/*
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
*/

import http from 'k6/http';
import { check, sleep } from 'k6';

const N = 100; // DAU 값 설정 (100명)
const dailyRequests = N * 2; // 1일 총 접속 수
const avgRPS = dailyRequests / 86400; // 1일 평균 RPS
const peakRPS = Math.max(1, Math.round(avgRPS * 10)); // 1일 최대 RPS (소수점 제거, 최소값 1이상)

export let options = {
    scenarios: {
        constant_load: {
            executor: 'constant-arrival-rate',
            rate: peakRPS, // 초당 요청 수 (최대 RPS)
            timeUnit: '1s',
            duration: '10m', // 테스트 지속 시간
            preAllocatedVUs: N, // VU 수 (DAU와 동일하게 설정)
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<200'], // 95% 요청의 응답 시간이 200ms 이하
        http_req_failed: ['rate<0.01'], // 실패율이 1% 미만
    },
};

export default function () {
    let res = http.get('http://localhost:8080/api/v2/movie-schedules?genre=FAMILY&title=S'); // 테스트 API
    check(res, {
        'is status 200': (r) => r.status === 200,
    });
    sleep(1); // 요청 간 간격 설정
}