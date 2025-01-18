import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
        // 캐시 없는 API 테스트
        no_cache: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '30s', target: 20 },
                { duration: '1m', target: 20 },
                { duration: '30s', target: 0 },
            ],
            exec: 'getNonCachedMovies',
        },
        // 캐시 있는 API 테스트
        with_cache: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '30s', target: 20 },
                { duration: '1m', target: 20 },
                { duration: '30s', target: 0 },
            ],
            exec: 'getCachedMovies',
        },
    },
    thresholds: {
        'http_req_duration': ['p(95)<500'],
        'http_req_failed': ['rate<0.01'],
        'checks': ['rate>0.9'], // 90% 이상의 체크가 성공해야 함
    },
};

const BASE_URL = 'http://localhost:8080';

// 헬스체크 함수 추가
export function setup() {
    const healthCheck = http.get(`${BASE_URL}/movies/now-showing`);
    if (healthCheck.status !== 200) {
        throw new Error('API is not responding');
    }
}

export function getNonCachedMovies() {
    const params = {
        headers: {
            'Accept': 'application/json',
        },
    };
    
    const response = http.get(
        `${BASE_URL}/movies/now-showing?title=Movie%201`,
        params
    );
    
    check(response, {
        'is status 200': (r) => r.status === 200,
        'response time < 500ms': (r) => r.timings.duration < 500,
        'has data': (r) => r.json().length > 0,
    });
    
    sleep(1);
}

export function getCachedMovies() {
    const params = {
        headers: {
            'Accept': 'application/json',
        },
    };
    
    const response = http.get(
        `${BASE_URL}/movies/now-showing/cached?title=Movie%201`,
        params
    );
    
    check(response, {
        'is status 200': (r) => r.status === 200,
        'response time < 200ms': (r) => r.timings.duration < 200,
        'has data': (r) => r.json().length > 0,
    });
    
    sleep(1);
} 