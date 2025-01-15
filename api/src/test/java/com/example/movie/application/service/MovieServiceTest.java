package com.example.movie.application.service;

import com.example.movie.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class MovieServiceTest {
    @MockitoBean
    private MovieRepository movieRepository;

    @Test
    public void 테스트(){

    }
}