package com.example.movie.controller

import com.example.movie.dto.ReservationResponse
import com.example.movie.request.ReservationRequest
import com.example.movie.service.ReservationUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reservations")
class ReservationController (
    private val reservationUseCase: ReservationUseCase
) {
    @PostMapping
    fun reserve(@RequestBody @Valid request: ReservationRequest) : List<ReservationResponse> {
        val reservations = reservationUseCase.reserve(request.userId, request.screeningId, request.seatIds)
        return reservations.map{ ReservationResponse.from(it) }
    }
}