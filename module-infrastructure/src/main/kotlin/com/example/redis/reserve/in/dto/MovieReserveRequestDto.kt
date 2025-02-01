package com.example.redis.reserve.`in`.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class MovieReserveRequestDto(
    @JsonProperty(value = "movie_id")
    @field:NotNull
    val movieId: Long,

    @JsonProperty(value = "user_id")
    @field:NotNull
    val userId: Long,

    @JsonProperty(value = "screening_id")
    @field:NotNull
    val screeningId: Long,

    @JsonProperty(value = "seats")
    @field:Valid
    @field:Size(min = 1, max = 5, message = "The seat must have one to five.")
    val seats: List<@Valid MovieReserveSeatRequestDto>
) {
}