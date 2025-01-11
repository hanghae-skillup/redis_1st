package kr.spartacodingclub.cinema.core.usecase

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.domain.Theater
import kr.spartacodingclub.cinema.core.repository.MovieRepository
import kr.spartacodingclub.cinema.core.repository.ScreeningRepository
import java.time.LocalDateTime

class GetMovieSchedulesUseCase(
    private val movieRepository: MovieRepository,
    private val screeningRepository: ScreeningRepository
) {
    fun execute(): List<MovieSchedule> {
        return movieRepository.findAllOrderByReleaseDateDesc()
            .map { movie ->
                println("\nProcessing movie: ${movie.title}")

                val screenings = screeningRepository.findAllByMovieIdOrderByStartTimeAsc(movie.id)
                    .map { screening ->
                        val theater = screening.theater
                        val screeningInfo = ScreeningInfo(
                            theater = theater,
                            startTime = screening.startTime
                        )

                        screeningInfo
                    }

                MovieSchedule(
                    movie = movie,
                    screenings = screenings
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
