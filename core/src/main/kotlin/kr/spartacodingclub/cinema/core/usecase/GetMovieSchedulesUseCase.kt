package kr.spartacodingclub.cinema.core.usecase

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.domain.Theater
import kr.spartacodingclub.cinema.core.repository.MovieRepository
import java.time.LocalDateTime

class GetMovieSchedulesUseCase(
    private val movieRepository: MovieRepository,
) {
    fun execute(): List<MovieSchedule> {
        return movieRepository.findAllWithScreeningsOrderByReleaseDateDesc()
            .map { movieWithScreenings ->
                MovieSchedule(
                    movie = movieWithScreenings.movie,
                    screenings = movieWithScreenings.screenings.map { screening ->
                        ScreeningInfo(
                            theater = screening.theater,
                            startTime = screening.startTime
                        )
                    }
                )
            }
    }
}

data class MovieSchedule(
    val movie: Movie,
    val screenings: List<ScreeningInfo>
)

data class ScreeningInfo(
    val theater: Theater,
    val startTime: LocalDateTime
)
