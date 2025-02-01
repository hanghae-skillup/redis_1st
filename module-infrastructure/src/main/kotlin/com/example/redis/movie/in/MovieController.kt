package com.example.redis.movie.`in`

import com.example.redis.annotations.RateLimited
import com.example.redis.reserve.`in`.dto.MovieReserveRequestDto
import com.example.redis.movie.`in`.dto.MovieResponseDto
import com.example.redis.movie.`in`.dto.MovieSearchRequestQueryDto
import com.example.redis.movie.`in`.mapper.MovieControllerMapper
import com.example.redis.reserve.`in`.ReserveUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val movieUseCase: MovieUseCase,
) {

    @RateLimited(10)
    @GetMapping
    fun gets(@ModelAttribute movieSearchRequestQuery: MovieSearchRequestQueryDto): ResponseEntity<MutableList<MovieResponseDto>> {
        val movies = this.movieUseCase.gets(movieSearchRequestQuery.title, movieSearchRequestQuery.genre)
        return ResponseEntity.ok(movies.stream().map { MovieResponseDto.toDto(it) }.toList())
    }

}