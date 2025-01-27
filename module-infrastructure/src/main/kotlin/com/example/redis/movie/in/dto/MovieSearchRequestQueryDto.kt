package com.example.redis.movie.`in`.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.RequestParam

class MovieSearchRequestQueryDto(
    @RequestParam("title", required = false)
    @Valid
    @Size(min = 0, max = 197, message = "The title must be at least 1 character and no more than 197 characters long.")
    val title: String?,
    @RequestParam("genre", required = false)
    @Valid
    @Size(min = 0, max = 197, message = "The genre must be at least 1 character and no more than 197 characters long.")
    val genre: String?
) {
}