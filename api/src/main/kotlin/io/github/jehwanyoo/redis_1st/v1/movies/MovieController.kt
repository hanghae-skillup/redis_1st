package io.github.jehwanyoo.redis_1st.v1.movies

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/v1")
class MovieController {

    @GetMapping("/movies")
    fun getMovies(): MovieResponse {
        return MovieResponse(
            movieId = UUID.randomUUID(),
            title = "범죄도시",
            rating = "청소년 관람불가",
            genre = "액션",
            releaseDate = LocalDate.of(2017, 5, 17),
            thumbnailUrl = "https://example.com/thumbnails/crime-city.jpg",
            runtimeMinutes = 121,
            screens = listOf(
                Screen(
                    screenId = UUID.randomUUID(),
                    screenName = "CGV 강남점",
                    row = 5,
                    column = 5,
                    showTimes = listOf(
                        ShowTime(
                            showTimeId = UUID.randomUUID(),
                            startTime = LocalDateTime.of(2025, 1, 10, 10, 0)
                        ),
                    ),
                ),
            ),
        )
    }
}
