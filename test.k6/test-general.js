import http from 'k6/http';
import { check, sleep } from 'k6';


 export let options = {
   stages: [
     { duration: '15s', target: 10 },
     { duration: '30s', target: 10 },
     { duration: '15s', target: 0 },
   ],
 };

export default function () {
  const url = 'http://localhost:8080/movies/now-showing';
  const params = { title: 'Movie 2', genre: null };

  const fullUrl = `${url}?title=${encodeURIComponent(params.title)}&genre=${encodeURIComponent(params.genre || '')}`;
  const res = http.get(fullUrl);
  check(res, {
    'General API: status is 200': (r) => r.status === 200,
    'General API: response time < 500ms': (r) => r.timings.duration < 500,
  });

  sleep(1);
}
