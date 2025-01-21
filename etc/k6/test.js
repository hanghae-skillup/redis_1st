import http from "k6/http";
import { sleep } from "k6";

export const options = {
    stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 100 },
        { duration: '5m', target: 200 },
        { duration: '2m', target: 100 },
        { duration: '1m', target: 50 },
    ]
};

export default function() {
    http.get("http://localhost:8080/api/v1/movies?genre=COMEDY&title=%EC%8B%A0");
    sleep(0.5);
}