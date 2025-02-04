package com.movie.domain.service;

import com.movie.domain.entity.Movie;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void getCurrentMovies_ReturnsCurrentMovies() {
        // Given
        Movie movie = TestFixture.createMovie();
        when(movieRepository.findCurrentMovies(any(LocalDateTime.class)))
                .thenReturn(List.of(movie));

        // When
        List<Movie> result = movieService.getCurrentMovies();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void getUpcomingMovies_ReturnsUpcomingMovies() {
        // Given
        Movie movie = TestFixture.createMovie();
        when(movieRepository.findUpcomingMovies(any(LocalDateTime.class)))
                .thenReturn(List.of(movie));

        // When
        List<Movie> result = movieService.getUpcomingMovies();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(movie.getTitle());
    }
} 