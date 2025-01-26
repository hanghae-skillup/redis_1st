import http from 'k6/http';
import { sleep } from 'k6';

// 공통 설정
const BASE_URL = 'http://localhost:8080';

// 타이틀과 장르의 예제 배열
const titles = Array.from({length: 20}, (_, i) => `${i + 1}`);
const genres = [null, 'ACTION', 'COMEDY', 'SF'];

// 랜덤한 타이틀 가져오기
function getRandomElement(arr) {
    return arr[Math.floor(Math.random() * arr.length)];
}

// 부하 테스트 옵션
export const options = {
    stages: [
        { duration: '2m', target: 100 },   // 초기 부하, 2m 동안 VU를 0에서 100으로 점진적으로 증가시킵니다.
        { duration: '5m', target: 1000 },  // 최대 부하로 증가, 5m 동안 VU를 100에서 1000으로 점진적으로 증가시킵니다.
        { duration: '2m', target: 0 }      // 종료, 2m 동안 VU를 1000에서 0으로 점진적으로 감소시킵니다.
    ],
    thresholds: {
        // 사용자 경험을 위한 주요 성능 지표
        http_req_duration: ['p(95)<200'], // 95%의 요청이 200ms 이하
        // 서비스 안정성 보장을 위한 조건
        http_req_failed: ['rate<0.01'],   // 실패율 1% 미만
    }
};

// 요청 생성 함수
function makeRequest() {
    const randomTitle = getRandomElement(titles);
    const randomGenre = getRandomElement(genres);

    // 쿼리 문자열 수동 생성
    const queryParams = [];
    if (randomTitle) {
        queryParams.push(`title=${encodeURIComponent(randomTitle)}`);
    }
    if (randomGenre) {
        queryParams.push(`genre=${encodeURIComponent(randomGenre)}`);
    }

    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    // 요청 보내기
    http.get(`${BASE_URL}/api/v1/movies${queryString}`);
    sleep(0.5);
}

export default function() {
    makeRequest();
}