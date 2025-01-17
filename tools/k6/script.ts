import http from 'k6/http';

import {sleep} from "k6"

export let options = {
    vus: 10,
    duration: '5m'
}

const BASE_URL = 'http://localhost:8080/api/movies'

export default function() {
    let getUrl = BASE_URL
    http.get(getUrl);
}
