package com.movie.api.integration;

import com.movie.api.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
@ActiveProfiles("test")
class MovieApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("영화 조회 API 테스트")
    void shouldGetMovie() {
        String url = "http://localhost:" + port + "/api/v1/movies/1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
} 