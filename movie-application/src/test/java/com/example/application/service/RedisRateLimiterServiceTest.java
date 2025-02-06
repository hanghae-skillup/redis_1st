package com.example.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@ExtendWith(MockitoExtension.class)
class RedisRateLimiterServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @InjectMocks
    private RedisRateLimiterService redisRateLimiterService;


    @DisplayName("RateLimiter가 요청을 허용하면 true를 반환한다.")
    @Test
    void givenAllowedRequest_whenIsAllowed_thenReturnTrue() {
        // Given
        String key = "test:rateLimit";
        when(redisTemplate.execute(any(DefaultRedisScript.class), eq(Collections.singletonList(key)), anyString(), anyString()))
                .thenReturn(1L); // Redis Lua Script가 1 반환 (허용)

        // When
        boolean result = redisRateLimiterService.isAllowed(key, 50, 60);

        // Then
        assertTrue(result, "Rate Limiting이 허용된 경우 true를 반환해야 합니다.");
    }

    @DisplayName("RateLimiter가 요청을 차단하면 false를 반환한다.")
    @Test
    void givenBlockedRequest_whenIsAllowed_thenReturnFalse() {
        // Given
        String key = "test:rateLimit";
        when(redisTemplate.execute(any(DefaultRedisScript.class), eq(Collections.singletonList(key)), anyString(), anyString()))
                .thenReturn(0L); // Redis Lua Script가 0 반환 (차단)

        // When
        boolean result = redisRateLimiterService.isAllowed(key, 50, 60);

        // Then
        assertFalse(result, "Rate Limiting이 초과된 경우 false를 반환해야 합니다.");
    }

}
