package com.example.repository;

import com.example.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("영화 목록은 최근 개봉일 순서로 정렬된다")
    void movie_sort() {
        Movie movie1 = new Movie("타이탄1", "https://localhost.com", 120, LocalDate.of(2024, 11, 21), Genre.ACTION, Rating.ALL);
        Movie movie2 = new Movie("타이탄2", "https://localhost.com", 120, LocalDate.of(2024, 11, 22), Genre.ACTION, Rating.ALL);
        Movie movie3 = new Movie("타이탄3", "https://localhost.com", 120, LocalDate.of(2024, 11, 23), Genre.ACTION, Rating.ALL);
        Movie movie4 = new Movie("타이탄4", "https://localhost.com", 120, LocalDate.of(2024, 11, 24), Genre.ACTION, Rating.ALL);

        movieRepository.saveAll(List.of(movie1, movie2, movie3, movie4));

        List<Movie> movies = movieRepository.findAllByOrderByReleaseDateDesc();
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
            System.out.println(movie.getMovieTheaters());
            System.out.println(movie.getReleaseDate());
        }
        assertThat(movies.getFirst().getTitle()).isEqualTo("타이탄4");
        assertThat(movies.get(1).getTitle()).isEqualTo("타이탄3");
        assertThat(movies.get(2).getTitle()).isEqualTo("타이탄2");
        assertThat(movies.getLast().getTitle()).isEqualTo("타이탄1");
    }
}