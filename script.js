import http from 'k6/http';
import { sleep } from 'k6';

export default function () {
  http.get('http://host.docker.internal:8080/api/v1/movie?page=0&size=10&title=e');
  sleep(1);
}