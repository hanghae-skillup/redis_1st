package com.example.movie.controller

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.dto.MovieResponse
import com.example.movie.request.MovieSearchRequest
import com.example.movie.service.NowPlayingMoviesUseCase
import jakarta.validation.Valid
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
    fun getMovies(@Valid movieSearchRequest: MovieSearchRequest): List<MovieResponse> {
        val movies = nowPlayingMoviesUseCase.getMoviesByStatus(movieSearchRequest.status, movieSearchRequest.title, movieSearchRequest.genreId)
        return movies.map { MovieResponse.from(it) }
    }
}