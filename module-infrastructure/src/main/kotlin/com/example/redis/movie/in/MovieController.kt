package com.example.redis.movie.`in`

import com.example.redis.movie.`in`.dto.MovieResponseDto
import com.example.redis.movie.`in`.movie.MovieUseCase
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val movieUseCase: MovieUseCase
) {

    @GetMapping
    fun gets(
        @RequestParam("title", required = false)
        @Valid
        @Size(min = 0, max = 197, message = "The title must be at least 1 character and no more than 197 characters long.") title: String?,
        @RequestParam("genre", required = false)
        @Valid
        @Size(min = 0, max = 197, message = "The genre must be at least 1 character and no more than 197 characters long.") genre: String?
    ): ResponseEntity<MutableList<MovieResponseDto>> {
        val movies = this.movieUseCase.gets(title, genre)
        return ResponseEntity.ok(movies.stream().map { MovieResponseDto.toDto(it) }.toList())
    }
}