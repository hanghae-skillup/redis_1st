package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RateLimiterTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String TEST_IP = "127.0.0.1";

    @AfterEach
    void cleanup() {
        redisTemplate.delete("request:" + TEST_IP);
        redisTemplate.delete("block:" + TEST_IP);
    }

    @Test
    void testGuavaRateLimiter_BlocksExcessRequests() throws Exception {
        String url = "http://localhost:" + port + "/movies/playing";

        // 첫 번째 요청
        ResponseEntity<String> response1 = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response1.getStatusCode());

        // 두 번째 요청
        ResponseEntity<String> response2 = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response2.getStatusCode());

        // 세 번째 요청
        ResponseEntity<String> response3 = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response3.getStatusCode());
    }

    @Test
    void testRedisRateLimit_UnderLimit() {
        String url = "http://localhost:" + port + "/movies/playing";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Forwarded-For", TEST_IP);

        for (int i = 0; i < 50; i++) {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Test
    void testRedisRateLimit_ExceedLimit() {
        String url = "http://localhost:" + port + "/movies/playing";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Forwarded-For", TEST_IP);

        // 49번 요청 실행
        for (int i = 0; i < 50; i++) {
            restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
        }

        // 50번째 요청은 차단되어야 함
        ResponseEntity<String> blockedResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, blockedResponse.getStatusCode());
    }

    @Test
    void testRedisRateLimit_Unblock() throws InterruptedException {
        String url = "http://localhost:" + port + "/movies/playing";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Forwarded-For", TEST_IP);

        // 제한 초과
        for (int i = 0; i < 50; i++) {
            restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
        }

        // 테스트를 위해 block이 해제되는 시간을 1분 후로 설정
        Thread.sleep(60 * 1000);
        ResponseEntity<String> responseAfterOneMin = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
        assertEquals(HttpStatus.OK, responseAfterOneMin.getStatusCode());
    }
}