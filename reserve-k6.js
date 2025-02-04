import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 100,
    duration: '600s',
};

export default function () {
    let payload = JSON.stringify({
        memberId: 3,
        screenId: 23,
        scheduleId: 88,
        seatNames: ["A1", "A2"]
    });

    let headers = { 'Content-Type': 'application/json' };

    let res = http.post('http://localhost:8081/api/v1/reservation', payload, { headers: headers });

    let ok = check(res, {
        'status is 200 or 400': (r) => r.status === 200 || r.status === 400
    });

    sleep(1);
}