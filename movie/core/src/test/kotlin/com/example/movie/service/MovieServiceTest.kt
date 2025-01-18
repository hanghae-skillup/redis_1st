package com.example.movie.service

import com.example.movie.domain.common.TimeHandler
import com.example.movie.domain.movie.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.domain.theater.model.Theater
import org.assertj.core.api.Assertions
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
class MovieServiceTest {
    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var timeHandler: TimeHandler

    @InjectMocks
    private lateinit var movieService: MovieService

    @Test
    fun `현재 상영중인 영화와 상영 정보를 조회한다`() {
        // given
        val currentTime = LocalDateTime.of(2024, 1, 2, 0, 0, 0)
        whenever(timeHandler.getCurrentTime()).thenReturn(currentTime)

        val theater = createTheater(currentTime)
        val screenings = listOf(
            createScreening(1L, currentTime, currentTime.plusHours(12), theater),
            createScreening(2L, currentTime, currentTime.plusHours(14), theater),
        )
        val movie = createMovie(currentTime, screenings)

        whenever(movieRepository.findAllByStatusWithMovieAndTheater(eq(currentTime),eq(ScreeningStatus.SCHEDULED), eq(null), eq(null)))
            .thenReturn(listOf(movie))

        // when
        val result = movieService.getMoviesByStatusAndTitleAndGenre(ScreeningStatus.SCHEDULED, null, null)

        // then
        Assertions.assertThat(result).hasSize(1)
        Assertions.assertThat(result.first()).usingRecursiveComparison().isEqualTo(movie)
        Assertions.assertThat(movie.screenings).hasSize(2)
        Assertions.assertThat(movie.screenings).usingRecursiveComparison().isEqualTo(screenings)
    }

    private fun createMovie(currentTime: LocalDateTime, screenings: List<Screening>): Movie {
        return Movie(
            id = 1,
            title = "title1",
            rating = Rating.ALL,
            genre = createGenre(currentTime),
            releaseDate = LocalDate.of(2024,1,1),
            thumbnailUrl = "https://image.testdb/image.jpg",
            runningTime = 120,
            screenings = screenings,
            createdBy = "test",
            createdAt = currentTime,
            updatedBy = "test",
            updatedAt = currentTime
        )
    }

    private fun createGenre(currentTime: LocalDateTime) : Genre {
        return Genre(
            id = 1,
            name = "action",
            description = "thrilling action",
            createdBy = "test",
            createdAt = currentTime,
            updatedBy = "test",
            updatedAt = currentTime
        )
    }

    private fun createScreening(id: Long, currentTime: LocalDateTime, screeningTime: LocalDateTime, theater: Theater): Screening {
        return Screening(
            id = id,
            movieId = 1,
            theater = theater,
            screeningTime = screeningTime,
            screeningEndTime = screeningTime.plusMinutes(120),
            status = ScreeningStatus.SCHEDULED,
            createdBy = "test",
            createdAt = currentTime,
            updatedBy = "test",
            updatedAt = currentTime

        )
    }

    private fun createTheater(currentTime: LocalDateTime): Theater {
        return Theater(
            id = 1,
            name = "cgv",
            createdBy = "test",
            createdAt = currentTime,
            updatedBy = "test",
            updatedAt = currentTime
        )
    }
}