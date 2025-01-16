package com.example.movie.application.convertor;

import com.example.movie.application.dto.MoviesNowShowingDetail;
import com.example.movie.entity.movie.Genre;
import com.example.movie.entity.movie.Grade;
import com.example.movie.repository.dto.MoviesNowShowingDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DtoConvertorTest {
    @Test
    @DisplayName("현재 상영중인 영화를 영화ID와 상영관을 기준으로 성공적으로 그룹핑한다.")
    void 현재상영중인_영화조회_그룹핑_성공(){
        //given
        LocalDateTime now = LocalDateTime.now();

        List<MoviesNowShowingDetailDto> dbResults = List.of(
                new MoviesNowShowingDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 1L, "Theater 1",
                        now.plusHours(1), now.plusHours(3)
                ),
                new MoviesNowShowingDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 1L, "Theater 1",
                        now.plusHours(3), now.plusHours(5)
                ),
                new MoviesNowShowingDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 2L, "Theater 2",
                        now.plusHours(2), now.plusHours(4)
                ),
                new MoviesNowShowingDetailDto(
                        2L, "Movie B", Grade.FROM_12_AGE, LocalDate.of(2024, 1, 10),
                        "thumbnailB.jpg", 130L, Genre.ROMANCE, 1L, "Theater 1",
                        now.plusHours(3), now.plusHours(5)
                )
        );

        //when
        DtoConvertor dtoConvertor = new DtoConvertor();
        List<MoviesNowShowingDetail> result = dtoConvertor.moviesNowScreening(dbResults);

        //then
        assertThat(result).hasSize(2); // 영화 ID로 그룹화 결과 두 개 (Movie A, Movie B)

        MoviesNowShowingDetail movieA = result.get(0);
        assertThat(movieA.movieId()).isEqualTo(1L); // Movie A
        assertThat(movieA.screeningsDetails()).hasSize(2); // Theater 1, Theater 2
        assertThat(movieA.screeningsDetails().get(0).screeningTimes())
                .extracting("startAt")
                .isSorted(); // Theater 1 시간표 정렬 검증
        assertThat(movieA.screeningsDetails().get(1).screeningTimes())
                .extracting("startAt")
                .isSorted(); // Theater 2 시간표 정렬 검증

        MoviesNowShowingDetail movieB = result.get(1);
        assertThat(movieB.movieId()).isEqualTo(2L); // Movie B
        assertThat(movieB.screeningsDetails()).hasSize(1); // Theater 1만 포함
        assertThat(movieB.screeningsDetails().get(0).screeningTimes())
                .extracting("startAt")
                .isSorted();
    }
}