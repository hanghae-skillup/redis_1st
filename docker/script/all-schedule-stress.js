import http from "k6/http";
import { check } from "k6";

const __VU__ = 10; // N 값 (DAU)

export const options = {
    scenarios: {
        // constant_load: {
        //     executor: 'constant-vus',
        //     vus: __VU__, // 동시 사용자 수 (DAU 설정)
        //     duration: '1m30s', // 테스트 지속 시간
        // },
        peak_load: {
            executor: 'ramping-vus',
            startVUs: __VU__, // 초기 동시 사용자 수
            stages: [
                { duration: '30s', target: __VU__ * 10 }, // 피크 트래픽으로 증가
                { duration: '30s', target: __VU__ * 10 },  // 피크 트래픽 유지
                { duration: '30s', target: __VU__ },      // 정상 트래픽으로 감소
            ],
            gracefulRampDown: '10s',
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<200'],   // 95% 요청이 200ms 이하
        http_req_failed: ['rate<0.01'],     // 실패율 1% 이하
    },
};

// 동시 사용자 수 (DAU 기반 설정)


export default function () {
    const payload = JSON.stringify({title: null, genre: null});
    const param = {headers: {'Content-Type': 'application/json'}}
    let response = http.post(`http://localhost:8080/api/schedule`, payload, param);
     // 응답 확인
    check(response, {
        'is status 200': (r) => r.status === 200,
    });
}