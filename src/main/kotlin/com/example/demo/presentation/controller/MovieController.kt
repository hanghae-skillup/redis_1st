package com.example.demo.presentation.controller

import com.example.demo.application.dto.MovieDto
import com.example.demo.application.service.MovieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(private val movieService: MovieService) {

    @GetMapping("/movies/now-showing")
    fun getNowShowingMovies(
        @RequestParam title: String?,
        @RequestParam genre: String?
    ): List<MovieDto> {
        return movieService.getNowShowingMovies(title, genre)
    }

    @GetMapping("/movies/now-showing/cached")
    fun getNowShowingMoviesWithCaching(
        @RequestParam title: String?,
        @RequestParam genre: String?
    ): List<MovieDto> {
        return movieService.getNowShowingMoviesWithCaching(title, genre)
    }
} 