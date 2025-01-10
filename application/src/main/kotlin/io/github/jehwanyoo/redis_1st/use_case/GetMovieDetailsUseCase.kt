package io.github.jehwanyoo.redis_1st.use_case

import io.github.jehwanyoo.redis_1st.aggregate.MovieDetail
import io.github.jehwanyoo.redis_1st.entity.Movie
import io.github.jehwanyoo.redis_1st.entity.Screen
import io.github.jehwanyoo.redis_1st.entity.ShowTime
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class GetMovieDetailsUseCase {

    fun execute(): MovieDetail {
        val movie = Movie(
            id = UUID.randomUUID(),
            title = "범죄도시",
            rating = "청소년 관람불가",
            genre = "액션",
            releaseDate = LocalDate.of(2017, 5, 17),
            thumbnailUrl = "https://example.com/thumbnails/crime-city.jpg",
            runtimeMinutes = 121,
        )

        val screen = Screen(
            id = UUID.randomUUID(),
            name = "CGV 강남점",
            row = 5,
            column = 5,
        )

        val showTime = ShowTime(
            id = UUID.randomUUID(),
            movieId = movie.id,
            screenId = screen.id,
            startTime = LocalDateTime.of(2025, 1, 10, 10, 0)
        )

        return MovieDetail(
            movie = movie,
            screens = listOf(screen),
            showTime = listOf(showTime),
        )
    }
}
