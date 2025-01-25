package com.example.redis.movie.`in`.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

class SeatRequestDto(
    @JsonProperty(value = "seat_id")
    @NotNull
    val seatId: Long,

    @JsonProperty(value = "seat_row")
    @NotNull
    val seatRow: String,

    @JsonProperty(value = "seat_col")
    @NotNull
    val seatCol: String
) {
}