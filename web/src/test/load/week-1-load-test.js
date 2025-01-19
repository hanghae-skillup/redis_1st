import http from 'k6/http';
import { check, sleep } from 'k6';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.2/index.js';

// CGV 기준 트래픽 추정치로 설정
const MAU = 3800000;
const WEEKDAY_RATIO = 0.04;
const WEEKEND_RATIO = 0.12;
const PEAK_MULTIPLIER = 2.5;

// 일일 활성 사용자 (DAU) 계산
const WEEKDAY_DAU = Math.floor(MAU * WEEKDAY_RATIO);    // 평일 DAU
const WEEKEND_DAU = Math.floor(MAU * WEEKEND_RATIO);     // 주말 DAU
const PEAK_DAU = Math.floor(WEEKEND_DAU * PEAK_MULTIPLIER); // 피크시간 DAU

// 사용자당 평균 세션 수
const SESSIONS_PER_USER = 2.5;

// RPS 계산
const calculateRPS = (dau) => {
    const DAILY_SESSIONS = dau * SESSIONS_PER_USER;
    return DAILY_SESSIONS / 86400; // 하루 총 초수로 나누기
};

// 테스트 설정
export const options = {
    scenarios: {
        // 평일 시나리오
        weekday_load: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '1m', target: 100 },     // 워밍업
                { duration: '3m', target: 500 },     // 점진적 증가
                { duration: '2m', target: 500 },     // 부하 유지
                { duration: '1m', target: 0 },       // 정리
            ],
        },
        // 주말 시나리오
        weekend_load: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '5m', target: Math.floor(WEEKEND_DAU * 0.1) },  // 10% 부하
                { duration: '10m', target: Math.floor(WEEKEND_DAU * 0.3) }, // 30% 부하
                { duration: '5m', target: 0 },                              // 정리
            ],
        },
        // 피크 시간대 시나리오 (대작 개봉일 + 주말 저녁)
        peak_load: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '5m', target: Math.floor(PEAK_DAU * 0.1) },     // 10% 부하
                { duration: '10m', target: Math.floor(PEAK_DAU * 0.3) },    // 30% 부하
                { duration: '5m', target: 0 },                              // 정리
            ],
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<200'],
        http_req_failed: ['rate<0.01'],
    },
};

const BASE_URL = 'http://localhost:8080';

export default function () {
    const headers = {
        'Content-Type': 'application/json',
    };

    const responses = {
        movieScreeningList: http.get(`${BASE_URL}/api/movies/schedules`, { headers }),
    };

    Object.entries(responses).forEach(([name, response]) => {
        check(response, {
            [`${name} status is 200`]: (r) => r.status === 200,
            [`${name} response time < 200ms`]: (r) => r.timings.duration < 200,
        });
    });

    sleep(2);
}

// 결과 보고서 생성
export function handleSummary(data) {
    const summary = {
        "테스트 결과 요약": {
            "총 요청 수": data.metrics.iterations.values.count,
            "평균 응답시간": `${(data.metrics.http_req_duration.values.avg).toFixed(2)}ms`,
            "최대 응답시간": `${(data.metrics.http_req_duration.values.max).toFixed(2)}ms`,
            "95퍼센타일": `${(data.metrics.http_req_duration.values["p(95)"]).toFixed(2)}ms`,
            "실패율": `${(data.metrics.http_req_failed.values.rate * 100).toFixed(2)}%`,
            "초당 요청 수(RPS)": (data.metrics.http_reqs.values.rate).toFixed(2)
        }
    };

    return {
        'stdout': textSummary(data, { indent: ' ', enableColors: true }),
        './test-results/summary.json': JSON.stringify(summary, null, 2),
        './test-results/detailed-report.json': JSON.stringify(data, null, 2),
    };
} 