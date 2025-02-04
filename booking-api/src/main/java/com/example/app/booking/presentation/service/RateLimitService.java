package com.example.app.booking.presentation.service;

import com.example.app.booking.presentation.dto.request.CreateBookingRequest;
import com.example.app.common.exception.RateLimitException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("UnstableApiUsage")
@Service
@RequiredArgsConstructor
public class RateLimitService {
    private final Cache<String, RateLimiter> rateLimiters = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build();

    public void checkAccessLimit(CreateBookingRequest createBookingRequest) throws ExecutionException {
        var key = String.format("%d:%d:%d:%d:%s",
                createBookingRequest.userId(),
                createBookingRequest.movieId(),
                createBookingRequest.showtimeId(),
                createBookingRequest.theaterId(),
                createBookingRequest.bookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        RateLimiter rateLimiter = rateLimiters.get(key, () -> RateLimiter.create(1.0/300.0));

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitException();
        }
    }
}
