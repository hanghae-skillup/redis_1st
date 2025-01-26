package com.example.app.booking.presentation.controller;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.presentation.dto.request.CreateBookingRequest;
import com.example.app.booking.usecase.CreateBookingUseCase;
import com.example.app.booking.usecase.SendMessageUseCase;
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
    private final SendMessageUseCase sendMessageUseCase;

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest)
            throws InterruptedException {
        var booking = createBookingUseCase.createBooking(createBookingRequest.toCreateBookingCommand());
        sendMessageUseCase.sendMessage(String.format("BookingId : %d, UserId : %d", booking.id(), booking.userId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
