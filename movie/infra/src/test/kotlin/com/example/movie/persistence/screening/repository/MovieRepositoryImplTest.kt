package com.example.movie.persistence.screening.repository

import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.movie.projection.MovieWithGenreDto
import com.example.movie.persistence.movie.projection.ScreeningWithTheaterDto
import com.example.movie.persistence.movie.repository.MovieJpaRepository
import com.example.movie.persistence.movie.repository.MovieRepositoryImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class MovieRepositoryImplTest {
    @Mock
    private lateinit var movieJpaRepository: MovieJpaRepository

    @InjectMocks
    private lateinit var movieRepository: MovieRepositoryImpl

    @Test
    fun `findCurrentScreeningsByMovieIdAndStatus 테스트`() {
        // given
        val currentTime = LocalDateTime.of(2024, 1, 2, 12, 0)
        val movieWithGenreDto = MovieWithGenreDto(
            id = 1,
            title = "title1",
            rating = Rating.ALL,
            releaseDate = LocalDate.of(2021, 1, 1),
            thumbnailUrl = "https://image.testdb/image.jpg",
            runningTime = 120,
            genreId = 1L,
            genreName = "action"
        )

        val screeningWithTheaterDto1 = ScreeningWithTheaterDto(
            id = 1,
            movieId = movieWithGenreDto.id,
            status = ScreeningStatus.SCHEDULED,
            screeningTime = currentTime.plusHours(1L),
            screeningEndTime = currentTime.plusHours(3L),
            theaterId = 1L,
            theaterName = "cgv"
        )

        val screeningWithTheaterDto2 = ScreeningWithTheaterDto(
            id = 2,
            movieId = movieWithGenreDto.id,
            status = ScreeningStatus.SCHEDULED,
            screeningTime = currentTime.plusHours(1L),
            screeningEndTime = currentTime.plusHours(3L),
            theaterId = 1L,
            theaterName = "cgv"
        )

        whenever(movieJpaRepository.findMoviesByCurrentTimeAndStatusAndTitleAndGenre(currentTime, ScreeningStatus.SCHEDULED, null, null))
            .thenReturn(listOf(movieWithGenreDto))
        whenever(movieJpaRepository.findScreeningsByMovieIds(eq(listOf(movieWithGenreDto.id)), eq(currentTime), eq(ScreeningStatus.SCHEDULED))).thenReturn(
            listOf(screeningWithTheaterDto1, screeningWithTheaterDto2)
        )

        // when
        val result = movieRepository.findAllByStatusWithMovieAndTheater(currentTime, status = ScreeningStatus.SCHEDULED, null, null)

        // then
        assertThat(result).hasSize(1)
        val first = result.first()
        val screenings = first.screenings
        assertThat(screenings).hasSize(2)

        assertThat(first.id).isEqualTo(movieWithGenreDto.id)
        assertThat(screenings[0].id).isEqualTo(screeningWithTheaterDto1.id)
        assertThat(screenings[1].id).isEqualTo(screeningWithTheaterDto2.id)
    }
}