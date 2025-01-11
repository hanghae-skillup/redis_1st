package kr.spartacodingclub.cinema.web.controller

import kr.spartacodingclub.cinema.core.usecase.GetMovieSchedulesUseCase
import kr.spartacodingclub.cinema.web.mapper.MovieScheduleMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/movies")
class MovieController(
    private val getMovieSchedulesUseCase: GetMovieSchedulesUseCase
) {
    @GetMapping("/schedules")
    fun getMovieSchedules(): List<MovieScheduleResponse> {
        return getMovieSchedulesUseCase.execute()
            .map(MovieScheduleMapper.INSTANCE::toMovieScheduleResponse)
    }
}

data class MovieScheduleResponse(
    val title: String,
    val rating: String,
    val releaseDate: String,
    val thumbnailUrl: String,
    val runningTime: Int,
    val genre: String,
    val screenings: List<ScreeningResponse>
)

data class ScreeningResponse(
    val theaterName: String,
    val startTime: String
)
