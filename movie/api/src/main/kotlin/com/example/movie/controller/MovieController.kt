package com.example.movie.controller

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.dto.MovieResponse
import com.example.movie.service.NowPlayingMoviesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase
) {
    @GetMapping
    fun getMovies(@RequestParam status: String): List<MovieResponse> {
        val screeningStatus = ScreeningStatus.valueOf(status.uppercase())
        val movieScreenings = nowPlayingMoviesUseCase.getMoviesByStatus(screeningStatus)
        return movieScreenings.map { (movie, screenings) ->
            MovieResponse.from(movie, screenings)
        }
    }
}