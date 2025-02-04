package com.movie.domain.repository;

import com.movie.domain.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    /*
    @Test
    void findCurrentMovies() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Movie movie1 = Movie.builder()
                .title("현재 상영작1")
                .grade("12세 이상")
                .genre("액션")
                .description("현재 상영중인 영화 1")
                .runningTime(120)
                .releaseDate(now.minusDays(5))
                .thumbnailUrl("http://example.com/movie1.jpg")
                .build();

        Movie movie2 = Movie.builder()
                .title("현재 상영작2")
                .grade("15세 이상")
                .genre("드라마")
                .description("현재 상영중인 영화 2")
                .runningTime(130)
                .releaseDate(now.minusDays(3))
                .thumbnailUrl("http://example.com/movie2.jpg")
                .build();

        Movie futureMovie = Movie.builder()
                .title("개봉 예정작")
                .grade("전체 관람가")
                .genre("애니메이션")
                .description("개봉 예정인 영화")
                .runningTime(90)
                .releaseDate(now.plusDays(1))
                .thumbnailUrl("http://example.com/future.jpg")
                .build();

        Movie pastMovie = Movie.builder()
                .title("상영 종료작")
                .grade("15세 이상")
                .genre("공포")
                .description("상영이 종료된 영화")
                .runningTime(110)
                .releaseDate(now.minusDays(10))
                .thumbnailUrl("http://example.com/past.jpg")
                .build();

        movieRepository.saveAll(List.of(movie1, movie2, futureMovie, pastMovie));

        // when
        List<Movie> currentMovies = movieRepository.findCurrentMovies(now);

        // then
        assertThat(currentMovies).hasSize(2)
                .extracting("title")
                .containsExactlyInAnyOrder("현재 상영작1", "현재 상영작2");
    }

    @Test
    void findUpcomingMovies() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Movie upcomingMovie1 = Movie.builder()
                .title("개봉 예정작1")
                .grade("12세 이상")
                .genre("액션")
                .description("개봉 예정인 영화 1")
                .runningTime(120)
                .releaseDate(now.plusDays(1))
                .thumbnailUrl("http://example.com/upcoming1.jpg")
                .build();

        Movie upcomingMovie2 = Movie.builder()
                .title("개봉 예정작2")
                .grade("15세 이상")
                .genre("드라마")
                .description("개봉 예정인 영화 2")
                .runningTime(130)
                .releaseDate(now.plusDays(2))
                .thumbnailUrl("http://example.com/upcoming2.jpg")
                .build();

        Movie currentMovie = Movie.builder()
                .title("현재 상영작")
                .grade("전체 관람가")
                .genre("애니메이션")
                .description("현재 상영중인 영화")
                .runningTime(90)
                .releaseDate(now.minusDays(1))
                .thumbnailUrl("http://example.com/current.jpg")
                .build();

        Movie pastMovie = Movie.builder()
                .title("상영 종료작")
                .grade("15세 이상")
                .genre("공포")
                .description("상영이 종료된 영화")
                .runningTime(110)
                .releaseDate(now.minusDays(10))
                .thumbnailUrl("http://example.com/past.jpg")
                .build();

        movieRepository.saveAll(List.of(upcomingMovie1, upcomingMovie2, currentMovie, pastMovie));

        // when
        List<Movie> upcomingMovies = movieRepository.findUpcomingMovies(now);

        // then
        assertThat(upcomingMovies).hasSize(2)
                .extracting("title")
                .containsExactlyInAnyOrder("개봉 예정작1", "개봉 예정작2");
    }
    */
} 