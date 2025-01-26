package com.example.redis.movie.`in`.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

class SeatRequestDto(
    @JsonProperty(value = "seat_id")
    @field:NotNull
    val seatId: Long,

    @JsonProperty(value = "seat_row")
    @field:NotNull
    val seatRow: String,

    @JsonProperty(value = "seat_col")
    @field:NotNull
    val seatCol: String
) {
}