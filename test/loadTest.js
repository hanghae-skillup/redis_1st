import http from 'k6/http';
import {check, sleep} from 'k6';

const DAU = 5000; // 하루 활성 사용자 수 (고정)
const avgRequestsPerUser = 2; // 한 사용자가 하루에 2번 요청한다고 가정
const dailyTotalRequests = DAU * avgRequestsPerUser;
const avgRPS = dailyTotalRequests / 86400; // 1초당 평균 요청 수 계산
const peakRPS = avgRPS * 10; // 피크 트래픽 10배 증가 고려

const VU_COUNT = 5000

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

export default function () {
    let res = http.get('http://localhost:8080/api/v1/movies?title=in&genre=SCI_FI'); // API 호출
    check(res, { 'is status 200': (r) => r.status === 200 });
    sleep(1); // 부하를 고르게 분배하기 위해 1초 대기
}
