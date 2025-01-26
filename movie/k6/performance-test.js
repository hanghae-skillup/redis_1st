import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
        cached_queries: {
            executor: 'ramping-arrival-rate',
            startRate: 1,
            timeUnit: '1s',
            preAllocatedVUs: 100,
            maxVUs: 500,
            stages: [
                { target: 100, duration: '1m' },
                { target: 100, duration: '2m' },
                { target: 0, duration: '1m' },
            ],
        },
    },
};

const MOVIE_TITLES = ['Steel', 'Laugh', 'Broken'];
const GENRE_IDS = [1, 2, 3];

export default function () {
    const queries = [
        'status=SCHEDULED',
        `status=SCHEDULED&title=${MOVIE_TITLES[Math.floor(Math.random() * MOVIE_TITLES.length)]}`,
        `status=SCHEDULED&genreId=${GENRE_IDS[Math.floor(Math.random() * GENRE_IDS.length)]}`,
        `status=SCHEDULED&title=${MOVIE_TITLES[Math.floor(Math.random() * MOVIE_TITLES.length)]}&genreId=${GENRE_IDS[Math.floor(Math.random() * GENRE_IDS.length)]}`,
    ];

    const queryString = queries[Math.floor(Math.random() * queries.length)];
    const response = http.get(`http://localhost:8080/api/v1/movies?${queryString}`);

    check(response, {
        'status is 200': (r) => r.status === 200,
        'response time < 200ms': (r) => r.timings.duration < 200,
    });

    sleep(1);
}