package com.movie.domain.repository;

import com.movie.domain.entity.Movie;
import com.movie.domain.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie currentMovie;
    private Movie upcomingMovie;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();

        currentMovie = Movie.builder()
                .title("Current Movie")
                .genre("Action")
                .description("Current Movie Description")
                .duration(120)
                .releaseDate(LocalDateTime.now().minusDays(1))
                .build();

        upcomingMovie = Movie.builder()
                .title("Upcoming Movie")
                .genre("Drama")
                .description("Upcoming Movie Description")
                .duration(150)
                .releaseDate(LocalDateTime.now().plusDays(7))
                .build();

        movieRepository.saveAll(List.of(currentMovie, upcomingMovie));
    }

    @Test
    void findCurrentMovies_ReturnsOnlyCurrentMovies() {
        // When
        List<Movie> result = movieRepository.findCurrentMovies(LocalDateTime.now());

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Current Movie");
    }

    @Test
    void findUpcomingMovies_ReturnsOnlyUpcomingMovies() {
        // When
        List<Movie> result = movieRepository.findUpcomingMovies(LocalDateTime.now());

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Upcoming Movie");
    }
} 