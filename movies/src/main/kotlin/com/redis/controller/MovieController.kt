package com.redis.controller

import com.redis.controller.dto.MovieResponse
import com.redis.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/movies")
class MovieController(
    private val movieService: MovieService,
) {
    @GetMapping
    fun gets(): ResponseEntity<MutableList<MovieResponse>> {
        return ResponseEntity.ok(movieService.gets());
    }
}