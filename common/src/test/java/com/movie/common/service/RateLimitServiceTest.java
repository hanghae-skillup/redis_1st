package com.movie.common.service;

import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.ratelimit.RateLimiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateLimitServiceTest {

    @Mock
    private RateLimiter rateLimiter;

    private RateLimitService rateLimitService;

    @BeforeEach
    void setUp() {
        rateLimitService = new TestRateLimitService(rateLimiter);
    }

    @Test
    void checkIpRateLimit_Success() {
        // Given
        String ip = "127.0.0.1";
        when(rateLimiter.tryAcquire(anyString())).thenReturn(true);

        // When
        rateLimitService.checkIpRateLimit(ip);

        // Then
        String expectedKey = "ip-rate-limit:" + ip;
        verify(rateLimiter).setRate(eq(expectedKey), eq(50), eq(1), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire(eq(expectedKey));
    }

    @Test
    void checkIpRateLimit_ExceedsLimit() {
        // Given
        String ip = "127.0.0.1";
        when(rateLimiter.tryAcquire(anyString())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> rateLimitService.checkIpRateLimit(ip))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("Too many requests from IP: " + ip);

        String expectedKey = "ip-rate-limit:" + ip;
        verify(rateLimiter).setRate(eq(expectedKey), eq(50), eq(1), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire(eq(expectedKey));
    }

    @Test
    void checkUserReservationRateLimit_Success() {
        // Given
        Long userId = 1L;
        String scheduleTime = "2024-03-20T10:00:00";
        when(rateLimiter.tryAcquire(anyString())).thenReturn(true);

        // When
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime);

        // Then
        String expectedKey = "user-reservation-rate-limit:" + userId + ":" + scheduleTime;
        verify(rateLimiter).setRate(eq(expectedKey), eq(1), eq(5), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire(eq(expectedKey));
    }

    @Test
    void checkUserReservationRateLimit_ExceedsLimit() {
        // Given
        Long userId = 1L;
        String scheduleTime = "2024-03-20T10:00:00";
        when(rateLimiter.tryAcquire(anyString())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> rateLimitService.checkUserReservationRateLimit(userId, scheduleTime))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("Too many reservation attempts");

        String expectedKey = "user-reservation-rate-limit:" + userId + ":" + scheduleTime;
        verify(rateLimiter).setRate(eq(expectedKey), eq(1), eq(5), eq(TimeUnit.MINUTES));
        verify(rateLimiter).tryAcquire(eq(expectedKey));
    }

    private static class TestRateLimitService implements RateLimitService {
        private final RateLimiter rateLimiter;

        TestRateLimitService(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public boolean checkIpRateLimit(String ip) {
            String key = "ip-rate-limit:" + ip;
            rateLimiter.setRate(key, 50, 1, TimeUnit.MINUTES);
            if (!rateLimiter.tryAcquire(key)) {
                throw new RateLimitExceededException("Too many requests from IP: " + ip);
            }
            return true;
        }

        @Override
        public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
            String key = "user-reservation-rate-limit:" + userId + ":" + scheduleTime;
            rateLimiter.setRate(key, 1, 5, TimeUnit.MINUTES);
            if (!rateLimiter.tryAcquire(key)) {
                throw new RateLimitExceededException("Too many reservation attempts");
            }
        }
    }
} 