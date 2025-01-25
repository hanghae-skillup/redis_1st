package com.example.app.booking.presentation.controller;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.presentation.dto.request.CreateBookingRequest;
import com.example.app.booking.usecase.CreateBookingUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest) {
        createBookingUseCase.createBooking(createBookingRequest.toCreateBookingCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
