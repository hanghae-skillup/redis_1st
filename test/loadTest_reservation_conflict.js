import http from 'k6/http';
import { check } from 'k6';

export let options = {
    scenarios: {
        burst_load: {
            executor: 'constant-vus', // 단기간 높은 동시 요청 발생
            vus: 500, // 500개의 동시 사용자 유지
            duration: '2s', // 2초 동안 모든 요청을 동시에 보냄
        },
        high_arrival_rate: {
            executor: 'constant-arrival-rate', // 초당 일정량의 요청 발생
            rate: 200, // 초당 200개의 요청 발생
            timeUnit: '1s', // 매 초마다
            duration: '5s', // 5초 동안 유지
            preAllocatedVUs: 300, // 미리 300개 VU를 할당하여 지연 없이 시작
        },
    },
    thresholds: {
        'http_req_duration': ['p(95)<500'], // 95%의 요청이 500ms 이하
        'http_req_failed': ['rate<0.01'],   // 실패율이 1% 미만
        'checks': ['rate>0.9'],             // 90% 이상의 요청이 기대한 상태 코드 응답을 받아야 함
    },
};

const BASE_URL = 'http://localhost:8080/api/v1/reservations';

// **고정된 Screening과 좌석을 사용하여 동시 예약 충돌을 유도**
const FIXED_SCREENING_ID = 1; // 특정 상영 ID 고정
const FIXED_SEAT_ID = 3; // 특정 좌석 ID 고정

export default function () {
    const payload = JSON.stringify({
        screeningId: FIXED_SCREENING_ID, // ✅ 같은 screening ID 사용
        memberId: Math.floor(Math.random() * 100) + 1, // 랜덤 회원 ID (1~100)
        seatIds: [FIXED_SEAT_ID]  // ✅ 같은 좌석 ID로 요청하여 충돌 유도
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(BASE_URL, payload, params);

    // 동시성 제어가 올바르게 동작하면 일부 요청에서 `409 Conflict` 발생해야 함.
    check(res, {
        'is status 200 or 201': (r) => r.status === 200 || r.status === 201, // 정상 예약
        'is status 409 (conflict)': (r) => r.status === 409, // 예약 충돌 발생 (정상적인 동작)
        'response time < 500ms': (r) => r.timings.duration < 500, // 500ms 이하 응답
    });
}
