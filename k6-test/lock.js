import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 10, // 동시 사용자 수
  duration: '2m', // 테스트 시간
};

export default function () {
  const url = 'http://localhost:8080/reservation/movie'; // API URL
  const payload = JSON.stringify({
    userId: 1,
    screeningId: 1,
    reservationSeatsId: [4, 5],
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
  sleep(1); // 요청 간 간격
}
