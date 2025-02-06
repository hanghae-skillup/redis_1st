package com.example.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.application.dto.request.MovieRequestDto;
import com.example.application.dto.request.MovieSearchCriteria;
import com.example.application.dto.request.ScreeningRequestDto;
import com.example.application.dto.response.MovieResponseDto;
import com.example.application.port.out.MovieRepositoryPort;
import com.example.application.port.out.ScreeningRepositoryPort;
import com.example.application.port.out.TheaterRepositoryPort;
import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.example.domain.model.entity.Movie;
import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.Theater;
import com.example.domain.model.valueObject.Genre;
import com.example.domain.model.valueObject.ContentRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock private MovieRepositoryPort movieRepositoryPort;
    @Mock private ScreeningRepositoryPort screeningRepositoryPort;
    @Mock private TheaterRepositoryPort theaterRepositoryPort;

    @InjectMocks private MovieService movieService;

    private Movie mockMovie;
    private Theater mockTheater;
    private MovieRequestDto movieRequestDto;
    private ScreeningRequestDto screeningRequestDto;

    @BeforeEach
    void setUp() {
        mockMovie = Movie.of(
                "Test Movie",
                ContentRating.ADULT,
                LocalDate.of(2023, 12, 1),
                "https://test-thumbnail.com",
                120,
                Genre.ACTION
        );

        mockTheater = mock(Theater.class);

        movieRequestDto = new MovieRequestDto(
                "Test Movie",
                ContentRating.ADULT,
                LocalDate.of(2023, 12, 1),
                "https://test-thumbnail.com",
                120,
                Genre.ACTION
        );

        screeningRequestDto = new ScreeningRequestDto(
                1L, // theaterId
                LocalTime.of(18, 30) // startTime
        );
    }

    @Test
    @DisplayName("제목과 장르로 영화를 검색하면 일치하는 목록을 반환한다.")
    void givenTitleAndGenre_whenFindMovies_thenReturnMovieList() {
        // Given
        MovieSearchCriteria criteria = new MovieSearchCriteria("Test", Genre.ACTION);
        Sort sort = Sort.by("releaseDate").descending();

        when(movieRepositoryPort.findBy(criteria, sort)).thenReturn(List.of(mockMovie));

        // When
        List<MovieResponseDto> result = movieService.findMovies(criteria);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Test Movie", result.getFirst().title());
        verify(movieRepositoryPort, times(1)).findBy(criteria, sort);
    }

    @Test
    @DisplayName("검색 결과가 없을 경우 빈 리스트를 반환한다.")
    void givenNoMatchingMovies_whenFindMovies_thenReturnEmptyList() {
        // Given
        MovieSearchCriteria criteria = new MovieSearchCriteria("Nonexistent", Genre.COMEDY);
        Sort sort = Sort.by("releaseDate").descending();

        when(movieRepositoryPort.findBy(criteria, sort)).thenReturn(Collections.emptyList());

        // When
        List<MovieResponseDto> result = movieService.findMovies(criteria);

        // Then
        assertTrue(result.isEmpty());
        verify(movieRepositoryPort, times(1)).findBy(criteria, sort);
    }

    @Test
    @DisplayName("영화 생성 요청이 들어오면 영화가 저장된다.")
    void givenValidRequest_whenCreateMovie_thenSaveMovie() {
        // When
        movieService.createMovie(movieRequestDto);

        // Then
        verify(movieRepositoryPort, times(1)).save(any(Movie.class));
    }

    @Test
    @DisplayName("올바른 영화와 상영관 정보를 제공하면 상영 일정이 저장된다.")
    void givenValidMovieAndTheater_whenAddScreening_thenSaveScreening() {
        // Given
        when(movieRepositoryPort.findById(1L)).thenReturn(Optional.of(mockMovie));
        when(theaterRepositoryPort.findById(1L)).thenReturn(Optional.of(mockTheater));

        // When
        movieService.addScreeningToMovie(1L, screeningRequestDto);

        // Then
        verify(screeningRepositoryPort, times(1)).save(any(Screening.class));
    }

    @Test
    @DisplayName("존재하지 않는 영화 ID로 상영 일정을 추가하면 예외가 발생한다.")
    void givenInvalidMovieId_whenAddScreening_thenThrowException() {
        // Given
        when(movieRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class,
                () -> movieService.addScreeningToMovie(1L, screeningRequestDto));

        assertEquals(ErrorCode.INVALID_MOVIE, exception.getErrorCode());
        verify(movieRepositoryPort, times(1)).findById(1L);
        verify(screeningRepositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("존재하지 않는 상영관 ID로 상영 일정을 추가하면 예외가 발생한다.")
    void givenInvalidTheaterId_whenAddScreening_thenThrowException() {
        // Given
        when(movieRepositoryPort.findById(1L)).thenReturn(Optional.of(mockMovie));
        when(theaterRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class,
                () -> movieService.addScreeningToMovie(1L, screeningRequestDto));

        assertEquals(ErrorCode.INVALID_THEATER, exception.getErrorCode());
        verify(movieRepositoryPort, times(1)).findById(1L);
        verify(theaterRepositoryPort, times(1)).findById(1L);
        verify(screeningRepositoryPort, never()).save(any()); // 상영 일정 저장 로직이 실행되지 않음
    }

}
