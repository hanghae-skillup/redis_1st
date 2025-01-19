import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 50 },  // 1분동안 50명의 가상 사용자로 증가
    { duration: '3m', target: 50 },  // 3분동안 50명 유지
    { duration: '1m', target: 0 },   // 1분동안 0명으로 감소
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이내에 완료되어야 함
  },
};

export default function () {
  const response = http.get('http://localhost:8080/api/v1/movies/now-showing');
  
  check(response, {
    'is status 200': (r) => r.status === 200,
    'response time < 500ms': (r) => r.timings.duration < 500,
  });

  sleep(1);
} 