package com.example.movie.controller

import com.example.movie.dto.MovieResponse
import com.example.movie.request.MovieSearchRequest
import com.example.movie.service.MovieSearchUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val movieSearchUseCase: MovieSearchUseCase
) {
    @GetMapping
    fun getMovies(@Valid movieSearchRequest: MovieSearchRequest): List<MovieResponse> {
        val movies = movieSearchUseCase.getMoviesByStatusAndTitleAndGenre(movieSearchRequest.status, movieSearchRequest.title, movieSearchRequest.genreId)
        if (movies.isEmpty()) return emptyList()
        return movies.map { MovieResponse.from(it) }
    }
}