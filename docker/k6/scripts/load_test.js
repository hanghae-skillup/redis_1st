import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '1m', target: 10 }, // 1분 동안 10명의 VU
        { duration: '2m', target: 50 }, // 2분 동안 50명의 VU 유지
        { duration: '1m', target: 0 },  // 1분 동안 VU 감소
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이내여야 함
        'http_reqs': ['rate>50'], // 초당 50개의 요청 이상 처리
    },
};

export default function () {
    const url = 'http://host.docker.internal:8080/api/v1/screenings/redis'; // 테스트할 엔드포인트

    const response = http.get(url);

    check(response, {
        "status is 200": (r) => r.status === 200,
        "response time is acceptable": (r) => r.timings.duration < 500,
    });
}
