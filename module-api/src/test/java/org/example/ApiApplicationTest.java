package org.example;

import com.google.common.util.concurrent.RateLimiter;
import org.example.repository.MovieJpaRepository;
import org.example.service.movie.FindMovieService;
import org.example.service.movie.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApiApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @Autowired
    private FindMovieService findMovieService;

    @Autowired
    private RateLimiter rateLimiter;

    @Autowired
    private MovieJpaRepository movieJpaRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRateLimiter_BlocksExcessRequests() throws Exception {
        String url = "http://localhost:" + port + "/movies/playing";

        // 첫 번째 요청
        ResponseEntity<String> response1 = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode());

        // 두 번째 요청
        ResponseEntity<String> response2 = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());

        // 세 번째 요청
        ResponseEntity<String> response3 = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.TOO_MANY_REQUESTS, response3.getStatusCode());
    }
}