package org.example.controller;

import org.example.baseresponse.BaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String TEST_IP = "127.0.0.1";

    @Test
    @DisplayName("상영 중인 영화 리스트를 조회한다.")
    void searchPlayingMovies_Success() {
        String url = "http://localhost:" + port + "/movies/playing?movieTitle=Inception&genre=SF&playing=true";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Forwarded-For", TEST_IP);


        ResponseEntity<BaseResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BaseResponse.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}