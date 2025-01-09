package com.redis.movies.controller

import com.redis.movies.controller.dto.MovieResponse
import com.redis.movies.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/movies")
class MovieController(
    private val movieService: MovieService,
) {
    @GetMapping("/")
    fun gets(): ResponseEntity<MutableList<MovieResponse>> {
        return ResponseEntity.ok(movieService.gets());
    }
}