import http from "k6/http";
import { sleep } from "k6";

export const options = {
  vus: 50,
  duration: "30s"
};

export default function() {
    http.get("http://localhost:8080/api/v1/movies?genre=COMEDY&title=%EC%8B%A0");
    sleep(0.5);
}