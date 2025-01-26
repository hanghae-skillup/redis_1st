import http from 'k6/http';
import { sleep } from 'k6';

// 공통 설정
const BASE_URL = 'http://localhost:8090';

const userIds = Array.from({ length: 10 }, (_, i) => `${i}`);
const movieIds = Array.from({ length: 20 }, (_, i) => `${i}`);
const seats = ['A1','A2','A3','A4','A5','B1','B2','B3','B4','B5','C1','C2','C3','C4','C5','D1','D2','D3','D4','D5','E1','E2','E3','E4','E5'];

function getRandomElement(arr) {
    return arr[Math.floor(Math.random() * arr.length)];
}

// 테스트 옵션
export const options = {
    duration: '1m',
    vus: 10,
    thresholds: {
        // 사용자 경험을 위한 주요 성능 지표
        http_req_duration: ['p(95)<200'], // 95%의 요청이 200ms 이하
    }
}

// 요청 생성
function makeRequest() {
    const params = { headers: { 'Content-Type': 'application/json' } };
    const userId = Number(getRandomElement(userIds));
    const movieId = Number(getRandomElement(movieIds));
    const seat1 = getRandomElement(seats);

    const payload = JSON.stringify({
        userId,
        movieId,
        showtimeId: 5,
        theaterId: 1,
        bookingDate: '2025-03-01',
        seats: [seat1]
    });

    http.post(`${BASE_URL}/api/v1/booking`, payload, params);
    sleep(0.5);
}

export default function() {
    makeRequest();
}