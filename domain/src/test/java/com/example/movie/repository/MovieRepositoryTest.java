package com.example.movie.repository;

import com.example.TestConfiguration;
import com.example.movie.entity.movie.Genre;
import com.example.movie.repository.dto.MoviesNowShowingDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("장르가 null이고 search가 파일인 경우 현재 상영중인 영화를 성공적으로 조회한다.")
    void 장르_null_search_파일_현재상영중인_영화조회_성공(){
        // given
        LocalDateTime now = LocalDateTime.now();
        String search = "파일";

        // when
        List<MoviesNowShowingDetailDto> result = movieRepository.findNowShowing(now, null, search);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(movie -> movie.getMovieName().contains("파일"));
    }

    @Test
    @DisplayName("장르가 SF이고 search가 null인 경우 현재 상영중인 영화를 성공적으로 조회한다.")
    void 장르_SF_search_null_현재상영중인_영화조회_성공(){
        // given
        LocalDateTime now = LocalDateTime.now();
        Genre genre = Genre.SF;

        // when
        List<MoviesNowShowingDetailDto> result = movieRepository.findNowShowing(now, genre, null);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(movie -> movie.getGenre().equals(Genre.SF));
    }

    @Test
    @DisplayName("장르가 SF이고 search가 파일인 경우 현재 상영중인 영화를 성공적으로 조회한다.")
    void 장르_SF_search_파일_현재상영중인_영화조회_성공(){
        // given
        LocalDateTime now = LocalDateTime.now();
        Genre genre = Genre.SF;
        String search = "파일";

        // when
        List<MoviesNowShowingDetailDto> result = movieRepository.findNowShowing(now, genre, search);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(movie -> movie.getGenre().equals(Genre.SF) && movie.getMovieName().contains("파일"));
    }

    @Test
    @DisplayName("장르와 search가 둘 다 null인 경우 현재 상영중인 영화를 성공적으로 조회한다.")
    void 장르_search_null_현재상영중인_영화조회_성공(){
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        List<MoviesNowShowingDetailDto> result = movieRepository.findNowShowing(now, null, null);

        // then
        assertThat(result).isNotEmpty();
    }
}