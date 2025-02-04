package com.example.app.booking.presentation.controller;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.presentation.dto.request.CreateBookingRequest;
import com.example.app.booking.presentation.service.RedisRateLimitService;
import com.example.app.booking.presentation.util.BookingKeyGenerator;
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
    private final RedisRateLimitService redisRateLimitService;

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest)
            throws InterruptedException {
        redisRateLimitService.checkAccessLimit(BookingKeyGenerator.generateRateLimitKey(createBookingRequest));

        var lockKey = BookingKeyGenerator.generateLockKey(createBookingRequest);
        var booking = createBookingUseCase.createBooking(lockKey, createBookingRequest.toCreateBookingCommand());
        sendMessageUseCase.sendMessage(String.format("BookingId : %d, UserId : %d", booking.id(), booking.userId()));

        redisRateLimitService.setAccessLimit(BookingKeyGenerator.generateRateLimitKey(createBookingRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
