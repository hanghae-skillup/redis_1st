package com.example.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.application.port.out.RateLimiterPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieSearchRateLimiterServiceTest {

    @Mock
    private RateLimiterPort rateLimiterPort;

    @InjectMocks
    private MovieSearchRateLimiterService movieSearchRateLimiterService;

    private String testIp;

    @BeforeEach
    void setUp() {
        testIp = "192.168.1.1";
    }

    @Test
    @DisplayName("첫 번째 요청은 허용되어야 한다.")
    void givenFirstRequest_whenCheckingIfAllowed_thenReturnTrue() {
        // Given
        String key = "search:" + testIp;
        when(rateLimiterPort.isAllowed(key, 50, 60)).thenReturn(true);

        // When
        boolean result = movieSearchRateLimiterService.isAllowed(testIp);

        // Then
        assertTrue(result, "첫 번째 요청은 허용되어야 합니다.");
        verify(rateLimiterPort, times(1)).isAllowed(key, 50, 60);
    }

    @Test
    @DisplayName("Rate Limiting 통과에 실패한 요청은 차단되어야 한다.")
    void givenRateLimitExceeded_whenCheckingIfAllowed_thenReturnFalse() {
        // Given
        String key = "search:" + testIp;
        when(rateLimiterPort.isAllowed(key, 50, 60)).thenReturn(false);

        // When
        boolean result = movieSearchRateLimiterService.isAllowed(testIp);

        // Then
        assertFalse(result, "요청 제한을 초과하면 차단되어야 합니다.");
        verify(rateLimiterPort, times(1)).isAllowed(key, 50, 60);
    }

}
