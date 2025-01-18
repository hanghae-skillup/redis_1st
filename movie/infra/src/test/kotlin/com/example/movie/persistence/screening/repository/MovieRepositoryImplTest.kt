package com.example.movie.persistence.screening.repository

import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.genre.projection.GenreProjection
import com.example.movie.persistence.movie.projection.MovieProjection
import com.example.movie.persistence.movie.repository.MovieJpaRepository
import com.example.movie.persistence.movie.repository.MovieRepositoryImpl
import com.example.movie.persistence.screening.projection.ScreeningProjection
import com.example.movie.persistence.theater.projection.TheaterProjection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
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

        val theaterProjection = mock(TheaterProjection::class.java)
        whenever(theaterProjection.id).thenReturn(1L)
        whenever(theaterProjection.name).thenReturn("cgv")

        val genreProjection = mock(GenreProjection::class.java)
        whenever(genreProjection.id).thenReturn(1L)
        whenever(genreProjection.name).thenReturn("action")

        val screeningProjection1 = mock(ScreeningProjection::class.java)
        whenever(screeningProjection1.id).thenReturn(1L)
        whenever(screeningProjection1.theater).thenReturn(theaterProjection)
        whenever(screeningProjection1.screeningTime).thenReturn(currentTime.plusHours(1))
        whenever(screeningProjection1.screeningEndTime).thenReturn(currentTime.plusHours(3))
        whenever(screeningProjection1.status).thenReturn(ScreeningStatus.SCHEDULED)

        val screeningProjection2 = mock(ScreeningProjection::class.java)
        whenever(screeningProjection2.id).thenReturn(2L)
        whenever(screeningProjection2.theater).thenReturn(theaterProjection)
        whenever(screeningProjection2.screeningTime).thenReturn(currentTime.plusHours(4))
        whenever(screeningProjection2.screeningEndTime).thenReturn(currentTime.plusHours(6))
        whenever(screeningProjection2.status).thenReturn(ScreeningStatus.SCHEDULED)

        val movieProjection = mock(MovieProjection::class.java)
        whenever(movieProjection.id).thenReturn(1L)
        whenever(movieProjection.title).thenReturn("title1")
        whenever(movieProjection.rating).thenReturn(Rating.ALL)
        whenever(movieProjection.releaseDate).thenReturn(LocalDate.of(2024,1,1))
        whenever(movieProjection.thumbnailUrl).thenReturn("https://image.testdb/image.jpg")
        whenever(movieProjection.runningTime).thenReturn(120)
        whenever(movieProjection.genre).thenReturn(genreProjection)
        whenever(movieProjection.screenings).thenReturn(listOf(screeningProjection1, screeningProjection2))

        whenever(movieJpaRepository.findMoviesByCurrentTimeAndStatusAndTitleAndGenre(currentTime, ScreeningStatus.SCHEDULED, null, null))
            .thenReturn(listOf(movieProjection))

        // when
        val result = movieRepository.findAllByStatusWithMovieAndTheater(currentTime, status = ScreeningStatus.SCHEDULED, null, null)

        // then
        assertThat(result).hasSize(1)
        val first = result.first()
        val screenings = first.screenings
        assertThat(screenings).hasSize(2)

        assertThat(first.id).isEqualTo(movieProjection.id)
        assertThat(screenings[0].id).isEqualTo(screeningProjection1.id)
        assertThat(screenings[1].id).isEqualTo(screeningProjection2.id)
    }
}