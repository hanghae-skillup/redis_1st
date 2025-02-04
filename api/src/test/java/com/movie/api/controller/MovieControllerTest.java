package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.config.TestConfig;
import com.movie.domain.entity.Movie;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@Import(TestConfig.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void getCurrentMovies() throws Exception {
        // Given
        Movie movie = TestFixture.createMovie();
        when(movieService.getCurrentMovies()).thenReturn(List.of(movie));

        // When & Then
        mockMvc.perform(get("/api/v1/movies/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$.data[0].genre").value(movie.getGenre()));
    }

    @Test
    void getUpcomingMovies() throws Exception {
        // Given
        Movie movie = TestFixture.createMovie();
        when(movieService.getUpcomingMovies()).thenReturn(List.of(movie));

        // When & Then
        mockMvc.perform(get("/api/v1/movies/upcoming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$.data[0].genre").value(movie.getGenre()));
    }
} 