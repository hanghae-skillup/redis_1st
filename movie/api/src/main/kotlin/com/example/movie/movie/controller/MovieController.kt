package com.example.movie.movie.controller

import com.example.movie.movie.dto.MovieResponse
import com.example.movie.service.NowPlayingMoviesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase
) {
    @GetMapping("/now-playing")
    fun getNowPlaying(): List<MovieResponse> {
        val movieScreenings = nowPlayingMoviesUseCase.getNowPlayingMovies()
        return movieScreenings.map { (movie, screenings) ->
            MovieResponse.from(movie, screenings)
        }
    }
}