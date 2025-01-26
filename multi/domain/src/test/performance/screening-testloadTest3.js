import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
stages: [
{ duration: '1m', target: 10000 }, // 1분 동안 50명의 유저 도달
{ duration: '1m', target: 100 }, // 3분 동안 유지
{ duration: '10s', target: 0 },  // 1분 동안 종료
],
};

export default function () {
// 공백을 %20으로 직접 바꾸기
const url = 'http://localhost:8080/screening?movieId=Movie%20Title%20459&genre=ACTION';

const res = http.get(url);

// 응답 상태와 시간 체크
check(res, {
    'status is 200': (r) => r.status === 200, // HTTP 200 확인
    'response time < 500ms': (r) => r.timings.duration < 500, // 500ms 이하 응답 시간
});
    // 1초 대기
    sleep(1);
}
