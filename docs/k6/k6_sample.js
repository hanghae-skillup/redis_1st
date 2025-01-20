//import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js"; // 상단의 추가면 테스트 결과 html로 시각화해 저장됨

import http from 'k6/http';
import { sleep } from 'k6';


export let options = {
//    vus: 30, // 가상 유저(Virtual Users) 수
//    duration: '2m', // 테스트 실행 시간 (초, 분, 시 단위 설정 가능)

    thresholds: {
      http_req_duration: ['p(95)<200'], // 95%의 요청이 200ms 이하
      http_req_failed: ['rate<0.01'],   // 실패율 1% 미만
    },

//    stages: [
//        { duration: '30s', target: 10 }, // 30초 동안 10명까지 증가
//        { duration: '1m', target: 50 },  // 1분 동안 10명 유지
//        { duration: '30s', target: 0 },  // 30초 동안 0명까지 감소 (테스트 종료)
//    ],
};

export default function () {
    http.get('http://localhost:8080/api/v2/movie-schedules'); //테스트 URL
    sleep(1);
}

// 하단의 추가면 테스트 결과 html로 시각화해 저장됨
//export function handleSummary(data) {
//    return {
//        //"summary.html" : htmlReport(data)  // 현재 경로에 저장
//        "C:\\k6\\summary.html" : htmlReport(data)  // 특정 경로에 저장
//    }
//}