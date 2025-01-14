package com.example.redis.movie.`in`

import com.example.redis.movie.`in`.dto.MovieResponseDto
import com.example.redis.movie.`in`.movie.MovieUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val movieUseCase: MovieUseCase
) {

    @GetMapping
    fun gets(): ResponseEntity<MutableList<MovieResponseDto>> {
        val movies = this.movieUseCase.gets()
        return ResponseEntity.ok(movies.stream().map { MovieResponseDto.toDto(it) }.toList())
    }
}