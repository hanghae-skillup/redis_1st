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

        val movie = createMovie(currentTime)
        val theater = createTheater(currentTime)
        val screenings = listOf(
            createScreening(1L, currentTime, currentTime.plusHours(12), movie, theater),
            createScreening(2L, currentTime, currentTime.plusHours(14), movie, theater),
            )
        val movieScreenings = mapOf(movie to screenings)

        whenever(movieRepository.findAllNowPlayingWithMovieAndTheater(eq(currentTime)))
            .thenReturn(movieScreenings)

        // when
        val result = movieService.getNowPlayingMovies()

        // then
        Assertions.assertThat(result).hasSize(1)
        Assertions.assertThat(result.keys.first()).usingRecursiveComparison().isEqualTo(movie)
        Assertions.assertThat(result[movie]).hasSize(2)
        Assertions.assertThat(result[movie]).usingRecursiveComparison().isEqualTo(screenings)
    }

    private fun createMovie(currentTime: LocalDateTime): Movie {
        return Movie(
            id = 1,
            title = "title1",
            rating = Rating.ALL,
            genre = createGenre(currentTime),
            releaseDate = LocalDate.of(2024,1,1),
            thumbnailUrl = "https://image.testdb/image.jpg",
            runningTime = 120,
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

    private fun createScreening(id: Long, currentTime: LocalDateTime, screeningTime: LocalDateTime, movie: Movie, theater: Theater): Screening {
        return Screening(
            id = id,
            movie = movie,
            theater = theater,
            screeningTime = screeningTime,
            screeningEndTime = screeningTime.plusMinutes(movie.runningTime.toLong()),
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