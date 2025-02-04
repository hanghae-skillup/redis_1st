package com.movie.api.service;

import com.movie.api.exception.RateLimitExceededException;
import com.movie.api.ratelimit.RateLimiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateLimitServiceTest {

    @Mock
    private RateLimiter rateLimiter;

    @InjectMocks
    private RateLimitService rateLimitService;

    @Test
    void checkIpRateLimit_Success() {
        // Given
        String ip = "127.0.0.1";
        when(rateLimiter.tryAcquire(any())).thenReturn(true);

        // When
        rateLimitService.checkIpRateLimit(ip);

        // Then
        verify(rateLimiter).setRate(eq("ip-rate-limit:" + ip), eq(50), eq(1), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire("ip-rate-limit:" + ip);
    }

    @Test
    void checkIpRateLimit_ExceedsLimit() {
        // Given
        String ip = "127.0.0.1";
        when(rateLimiter.tryAcquire(any())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> rateLimitService.checkIpRateLimit(ip))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("Too many requests from IP: " + ip);
    }

    @Test
    void checkUserReservationRateLimit_Success() {
        // Given
        Long userId = 1L;
        String scheduleTime = "2024-03-20T10:00:00";
        when(rateLimiter.tryAcquire(any())).thenReturn(true);

        // When
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime);

        // Then
        String expectedKey = "user-reservation-rate-limit:" + userId + ":" + scheduleTime;
        verify(rateLimiter).setRate(eq(expectedKey), eq(1), eq(5), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire(expectedKey);
    }

    @Test
    void checkUserReservationRateLimit_ExceedsLimit() {
        // Given
        Long userId = 1L;
        String scheduleTime = "2024-03-20T10:00:00";
        when(rateLimiter.tryAcquire(any())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> rateLimitService.checkUserReservationRateLimit(userId, scheduleTime))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("Too many reservation attempts");
    }
} 