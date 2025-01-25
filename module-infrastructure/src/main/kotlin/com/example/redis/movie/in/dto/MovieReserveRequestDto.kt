package com.example.redis.movie.`in`.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class MovieReserveRequestDto(
    @JsonProperty(value = "movie_id")
    @NotNull
    val movieId: Long,

    @JsonProperty(value = "user_id")
    @NotNull
    val userId: Long,

    @JsonProperty(value = "screening_id")
    @NotNull
    val screeningId: Long,

    @JsonProperty(value = "seats")
    @Size(min = 1, max = 5, message = "The seat must have one to five.")
    @Valid
    val seats: List<SeatRequestDto>
) {
}