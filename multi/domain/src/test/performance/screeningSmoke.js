import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 1, // 동시 사용자 수
    duration: '30s', // 테스트 실행 시간
};

export default function () {
    const url = 'http://localhost:8080/screening'
    // HTTP GET 요청
    const res = http.get(url);

    // 응답 상태와 시간 체크
    check(res, {
        'status is 200': (r) => r.status === 200, // HTTP 200 확인
        'response time < 500ms': (r) => r.timings.duration < 500, // 500ms 이하 응답 시간
    });

    // 1초 대기
    sleep(1);
}
