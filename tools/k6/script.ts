import http from 'k6/http';

import {sleep} from "k6"

export let options = {
    vus: 10,
    duration: '5m'
}

const BASE_URL = 'http://localhost:8080/api/search?title=title'

export default function() {
    
    let getUrl = BASE_URL
    for (let titleid=1; titleid<=100; titleid++) {
        http.get(getUrl+titleid);
        // console.log(getUrl+titleid);
    }
    
}
