import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
      { duration: '1m', target: 50 },   // 1분 동안 50명으로 증가
      { duration: '2m', target: 200 },  // 2분 동안 200명으로 증가
      { duration: '1m', target: 200 },  // 1분 동안 200명 유지
      { duration: '1m', target: 0 },    // 1분 동안 부하를 0으로 감소
  ],
};

export default function () {
  const res = http.get('http://localhost:8080/screening/movies?title=Movie1&genre=HORROR'); // API 엔드포인트
  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time < 200ms': (r) => r.timings.duration < 200,
  });
  sleep(1); // 요청 간 간격(초 단위)
}
