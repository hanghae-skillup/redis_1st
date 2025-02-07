package org.example.controller;

import org.example.service.movie.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @DisplayName("상영 중인 영화를 조회한다.")
    @Test
    void getPlayingMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/playing"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}