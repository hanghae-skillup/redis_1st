package com.movie.common.ratelimit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisRateLimiterTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RRateLimiter rRateLimiter;

    private RedisRateLimiter rateLimiter;

    @BeforeEach
    void setUp() {
        rateLimiter = new RedisRateLimiter(redissonClient);
        when(redissonClient.getRateLimiter(anyString())).thenReturn(rRateLimiter);
    }

    @Test
    void tryAcquire_Success() {
        // Given
        String key = "test-key";
        when(rRateLimiter.tryAcquire()).thenReturn(true);

        // When
        boolean result = rateLimiter.tryAcquire(key);

        // Then
        assertThat(result).isTrue();
        verify(redissonClient).getRateLimiter(eq(key));
        verify(rRateLimiter).tryAcquire();
    }

    @Test
    void tryAcquire_Failure() {
        // Given
        String key = "test-key";
        when(rRateLimiter.tryAcquire()).thenReturn(false);

        // When
        boolean result = rateLimiter.tryAcquire(key);

        // Then
        assertThat(result).isFalse();
        verify(redissonClient).getRateLimiter(eq(key));
        verify(rRateLimiter).tryAcquire();
    }

    @Test
    void setRate_Success() {
        // Given
        String key = "test-key";
        int permits = 50;
        int interval = 1;
        TimeUnit unit = TimeUnit.MINUTES;
        RRateLimiter rateLimiter = mock(RRateLimiter.class);
        when(redissonClient.getRateLimiter(key)).thenReturn(rateLimiter);
        when(rateLimiter.trySetRate(RateType.OVERALL, permits, interval, RateIntervalUnit.MINUTES)).thenReturn(true);

        // When
        this.rateLimiter.setRate(key, permits, interval, unit);

        // Then
        verify(redissonClient).getRateLimiter(key);
        verify(rateLimiter).trySetRate(RateType.OVERALL, permits, interval, RateIntervalUnit.MINUTES);
    }

    @Test
    void setRate_Failure() {
        // Given
        String key = "test-key";
        int permits = 50;
        int interval = 1;
        TimeUnit unit = TimeUnit.MINUTES;
        RRateLimiter rateLimiter = mock(RRateLimiter.class);
        when(redissonClient.getRateLimiter(key)).thenReturn(rateLimiter);
        when(rateLimiter.trySetRate(RateType.OVERALL, permits, interval, RateIntervalUnit.MINUTES)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> this.rateLimiter.setRate(key, permits, interval, unit))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Failed to set rate limit for key: " + key);

        verify(redissonClient).getRateLimiter(key);
        verify(rateLimiter).trySetRate(RateType.OVERALL, permits, interval, RateIntervalUnit.MINUTES);
    }

    @Test
    void setRate_UnsupportedTimeUnit() {
        // Given
        String key = "test-key";

        // When & Then
        assertThatThrownBy(() -> rateLimiter.setRate(key, 50, 1, TimeUnit.NANOSECONDS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unsupported time unit");
    }
} 