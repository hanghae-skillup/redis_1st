package com.example.movie.service;

import com.example.movie.converter.DtoConverter;
import com.example.movie.dto.MoviesDetailResponse;
import com.example.jpa.entity.movie.Genre;
import com.example.jpa.entity.movie.Grade;
import com.example.jpa.repository.movie.MovieRepository;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;
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
    private DtoConverter dtoConvertor;
    @InjectMocks
    MovieService movieService;

    @Test
    @DisplayName("현재 상영중인 영화를 개봉일 순으로 정렬한다..")
    void 현재상영중인_영화조회_개봉일순_정렬_성공(){
        //given
        LocalDateTime now = LocalDateTime.now();
        List<MoviesDetailDto> mockDbResults = initMoviesDetailDtoData(now);
        List<MoviesDetailResponse> mockConvertedResults = initMoviesDetailData();

        when(movieRepository.searchWithFiltering(now,null,null)).thenReturn(mockDbResults);
        when(dtoConvertor.moviesNowScreening(mockDbResults)).thenReturn(mockConvertedResults);

        //when
        List<MoviesDetailResponse> result = movieService.getMovies(now, Boolean.TRUE, null, null);

        //then
        assertThat(result.get(0).movieId()).isEqualTo(2L);
        assertThat(result.get(1).movieId()).isEqualTo(1L);
    }

    private static List<MoviesDetailResponse> initMoviesDetailData() {
        List<MoviesDetailResponse> mockConvertedResults = List.of(
                new MoviesDetailResponse(
                        1L, "Movie A", Grade.ALL_AGE, LocalDate.of(2023, 12, 15),
                        "thumbnailA.jpg", 120L, Genre.ACTION, List.of()
                ),
                new MoviesDetailResponse(
                        2L, "Movie B", Grade.FROM_12_AGE, LocalDate.of(2024, 1, 10),
                        "thumbnailB.jpg", 130L, Genre.ROMANCE, List.of()
                )
        );
        return mockConvertedResults;
    }

    private static List<MoviesDetailDto> initMoviesDetailDtoData(LocalDateTime now) {
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
        return mockDbResults;
    }
}