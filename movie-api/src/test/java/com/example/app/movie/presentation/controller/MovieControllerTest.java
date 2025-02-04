package com.example.app.movie.presentation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.app.common.exception.RateLimitException;
import com.example.app.movie.presentation.config.EmbeddedRedisConfig;
import com.example.app.movie.presentation.dto.request.MovieSearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
@Sql(scripts = "/movie-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieControllerTest {

    @Autowired
    private MovieController sut;

    @Test
    public void 영화_리스트_검색() {
        var searchRequest = new MovieSearchRequest("탑건", "ACTION");
        var response = sut.searchMovies(searchRequest, "127.0.0.1");
        var movies = response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(movies.size(), 2);
    }

    @Test
    public void Rate_Limit_1분_50요청_테스트() {
        var searchRequest = new MovieSearchRequest("탑건", "ACTION");

        assertThrows(RateLimitException.class, () -> {
            for (int i=0; i < 50; i++) {
                sut.searchMovies(searchRequest, "127.0.0.1");
            }
        });
    }
}
