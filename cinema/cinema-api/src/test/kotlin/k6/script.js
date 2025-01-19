import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 1000 }, // 10초 동안 1000명의 사용자 도달
        { duration: '20s', target: 2000 }, // 20초 동안 20000명 유지
        { duration: '10s', target: 0 }  // 10초 동안 종료
    ],
};

const BASE_URL = 'http://localhost:8080/api/v1/cinemas'; // 컨트롤러 기반 URL

export default function () {
    const cinemaId = 1; // 필요에 따라 다른 ID로 변경 가능
    const url = `${BASE_URL}/${cinemaId}/schedules`; // 정확한 URL 반영

    const res = http.get(url);

    // 응답 확인
    check(res, {
        'status is 200': (r) => r.status === 200,
        'response body is not empty': (r) => r.body && r.body.length > 0,
        'response time < 200ms': (r) => r.timings.duration < 200,
    });

    sleep(1); // 1초 대기
}