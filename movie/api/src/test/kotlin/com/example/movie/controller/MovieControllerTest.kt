package com.example.movie.controller

import com.example.movie.domain.movie.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.domain.theater.model.Theater
import com.example.movie.service.NowPlayingMoviesUseCase
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalDateTime

@WebMvcTest(MovieController::class)
class MovieControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var nowPlayingMoviesUseCase: NowPlayingMoviesUseCase

    @Test
    fun `현재 상영중인 영화 목록을 응답한다`() {
        val currentTime = LocalDateTime.of(2024, 1, 2, 0, 0, 0)
        val movie = createMovie(currentTime)
        val theater = createTheater(currentTime)
        val screenings = listOf(
            createScreening(currentTime, currentTime.plusHours(12), movie, theater),
            createScreening(currentTime, currentTime.plusHours(14), movie, theater),
        )
        val movieScreenings = mapOf(movie to screenings)

        whenever(nowPlayingMoviesUseCase.getMoviesByStatus(ScreeningStatus.SCHEDULED))
            .thenReturn(movieScreenings)

        mockMvc.perform(get("/api/v1/movies").param("status", "scheduled"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(movie.id))
            .andExpect(jsonPath("$[0].screenings.length()").value(2))
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

    private fun createScreening(currentTime: LocalDateTime, screeningTime: LocalDateTime, movie: Movie, theater: Theater): Screening {
        return Screening(
            id = 1,
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