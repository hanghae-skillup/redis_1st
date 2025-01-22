package com.example.movie.application.service;

import com.example.movie.application.convertor.DtoConvertor;
import com.example.movie.application.dto.MoviesDetail;
import com.example.movie.entity.movie.Genre;
import com.example.movie.entity.movie.Grade;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.dto.MoviesDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private DtoConvertor dtoConvertor;
    @InjectMocks
    MovieService movieService;

    @Test
    @DisplayName("현재 상영중인 영화를 개봉일 순으로 정렬한다..")
    void 현재상영중인_영화조회_개봉일순_정렬_성공(){
        //given
        LocalDateTime now = LocalDateTime.now();

        List<MoviesDetailDto> mockDbResults = List.of(
                new MoviesDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 1L, "Theater 1",
                        now.plusHours(1), now.plusHours(3)
                ),
                new MoviesDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 1L, "Theater 1",
                        now.plusHours(3), now.plusHours(5)
                ),
                new MoviesDetailDto(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, 2L, "Theater 2",
                        now.plusHours(2), now.plusHours(4)
                ),
                new MoviesDetailDto(
                        2L, "Movie B", Grade.FROM_12_AGE, LocalDate.of(2024, 1, 10),
                        "thumbnailB.jpg", 130L, Genre.ROMANCE, 1L, "Theater 1",
                        now.plusHours(3), now.plusHours(5)
                )
        );

        List<MoviesDetail> mockConvertedResults = List.of(
                new MoviesDetail(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, List.of()
                ),
                new MoviesDetail(
                        2L, "Movie B", Grade.FROM_12_AGE, LocalDate.of(2024, 1, 10),
                        "thumbnailB.jpg", 130L, Genre.ROMANCE, List.of()
                )
        );

        when(movieRepository.findAll(now,null,null)).thenReturn(mockDbResults);
        when(dtoConvertor.moviesNowScreening(mockDbResults)).thenReturn(mockConvertedResults);

        //when
        List<MoviesDetail> result = movieService.getMovies(now, Boolean.TRUE, null, null);

        //then
        assertThat(result.get(0).movieId()).isEqualTo(2L);
        assertThat(result.get(1).movieId()).isEqualTo(1L);
    }
}