import http from 'k6/http';
import {check, sleep} from 'k6';

// 검색어 풀 (자주 검색되는 키워드 & 다양한 장르)
const titles = ["in", "10", "20", "30", "40"];
const genres = ["SCI_FI", "ACTION", "DRAMA", "COMEDY", "THRILLER"];

// 자주 검색되는 키워드 비율 가중치 설정
function weightedRandomSelection(arr) {
    const weights = [0.4, 0.2, 0.05, 0.15, 0.2];
    const sum = weights.reduce((acc, weight) => acc + weight, 0);
    let rand = Math.random() * sum;

    for (let i = 0; i < arr.length; i++) {
        if (rand < weights[i]) {
            return arr[i];
        }
        rand -= weights[i];
    }
    return arr[arr.length - 1]; // fallback
}

// 하루 활성 사용자 수 & 요청 비율 계산
const DAU = 5000; // 하루 활성 사용자 수
const avgRequestsPerUser = 2; // 1인당 평균 검색 횟수
const dailyTotalRequests = DAU * avgRequestsPerUser;
const avgRPS = dailyTotalRequests / 86400; // 초당 평균 요청 수
const peakRPS = avgRPS * 10; // 피크 트래픽 (10배 증가)

const VU_COUNT = 5000;

export let options = {
    stages: [
        { duration: '2m', target: VU_COUNT }, // 2분 동안 서서히 증가
        { duration: '5m', target: VU_COUNT }, // 5분 동안 유지
        { duration: '2m', target: VU_COUNT }, // 2분 동안 유지
        { duration: '1m', target: 0 }, // 1분 동안 종료
    ],
    thresholds: {
        'http_req_duration': ['p(95)<200'], // 95% 요청이 200ms 이하
        'http_req_failed': ['rate<0.01'], // 실패율 1% 이하
    },
};

// 부하 테스트 함수
export default function () {
    let title = weightedRandomSelection(titles);  // 랜덤한 검색어 선택 (가중치 적용)
    let genre = genres[Math.floor(Math.random() * genres.length)]; // 장르 랜덤 선택

    let url = `http://localhost:8080/api/v1/movies?title=${title}&genre=${genre}`;

    let res = http.get(url); // API 요청 실행
    check(res, { 'is status 200': (r) => r.status === 200 });

    sleep(1); // 요청 간격 분배
}
