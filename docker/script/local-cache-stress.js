import http from "k6/http";
import { check } from "k6";
import {randomIntBetween, randomItem} from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

const __VU__ = 50; // N 값 (DAU)

export const options = {
    scenarios: {
        peak_load: {
            executor: 'ramping-vus',
            startVUs: __VU__,                               // 초기 동시 사용자 수
            stages: [
                { duration: '30s', target: __VU__ * 10 },   // 피크 트래픽으로 증가
                { duration: '30s', target: __VU__ * 10 },   // 피크 트래픽 유지
                { duration: '30s', target: __VU__ },        // 정상 트래픽으로 감소
            ],
            gracefulRampDown: '10s',
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<200'],                   // 95% 요청이 200ms 이하
        http_req_failed: ['rate<0.01'],                     // 실패율 1% 이하
    },
};

export default function () {
    const genres = ['ACTION', 'ROMANCE', 'HORROR', 'SF', 'ANIMATION'];
    const genre = randomItem(genres);                           // `장르` 랜덤 검색
    const title = `영화${randomIntBetween(1, 100)}`;      // '영화'명 랜덤 검색

    const payload = JSON.stringify({title, genre});
    const param = {headers: {'Content-Type': 'application/json'}}
    let response = http.post(`http://localhost:8080/api/schedules/cached`, payload, param);
    // 응답 확인
    check(response, {
        'is status 200': (r) => r.status === 200,
    });
}