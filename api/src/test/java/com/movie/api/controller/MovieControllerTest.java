package com.movie.api.controller;

import com.movie.api.service.RateLimitService;
import com.movie.api.support.IntegrationTest;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.domain.entity.Movie;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends IntegrationTest {

    @MockBean
    private MovieService movieService;

    @MockBean
    private RateLimitService rateLimitService;

    @Test
    void getCurrentMovies_Success() throws Exception {
        Movie movie = TestFixture.createMovie();
        when(movieService.getCurrentMovies()).thenReturn(List.of(movie));

        mockMvc.perform(get("/api/v1/movies/current")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.code").value("SUCCESS"))
                .andExpect(jsonPath("$.data[0].title").value(movie.getTitle()));
    }

    @Test
    void getCurrentMovies_RateLimitExceeded() throws Exception {
        doThrow(new RateLimitExceededException("Too many requests"))
                .when(rateLimitService).checkIpRateLimit(any());

        mockMvc.perform(get("/api/v1/movies/current")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.status").value("TOO_MANY_REQUESTS"))
                .andExpect(jsonPath("$.code").value("RATE_LIMIT_EXCEEDED"))
                .andExpect(jsonPath("$.message").value("Too many requests"));
    }

    @Test
    void getUpcomingMovies_Success() throws Exception {
        Movie movie = TestFixture.createMovie();
        when(movieService.getUpcomingMovies()).thenReturn(List.of(movie));

        mockMvc.perform(get("/api/v1/movies/upcoming")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.code").value("SUCCESS"))
                .andExpect(jsonPath("$.data[0].title").value(movie.getTitle()));
    }

    @Test
    void getUpcomingMovies_RateLimitExceeded() throws Exception {
        doThrow(new RateLimitExceededException("Too many requests"))
                .when(rateLimitService).checkIpRateLimit(any());

        mockMvc.perform(get("/api/v1/movies/upcoming")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.status").value("TOO_MANY_REQUESTS"))
                .andExpect(jsonPath("$.code").value("RATE_LIMIT_EXCEEDED"))
                .andExpect(jsonPath("$.message").value("Too many requests"));
    }
} 