package com.example.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.application.port.out.RateLimiterPort;
import com.example.application.service.MovieReservationRateLimiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieReservationRateLimiterServiceTest {

    @Mock
    private RateLimiterPort rateLimiterPort;

    @InjectMocks
    private MovieReservationRateLimiterService movieReservationRateLimiterService;

    private Long screeningId;

    @BeforeEach
    void setUp() {
        screeningId = 1L;
    }

    @DisplayName("첫 번째 요청은 허용되어야 한다.")
    @Test
    void givenFirstRequest_whenCheckingIfAllowed_thenReturnTrue() {
        // Given
        String key = "reservation:" + screeningId;
        when(rateLimiterPort.isAllowed(key, 1, 300)).thenReturn(true);

        // When
        boolean result = movieReservationRateLimiterService.isAllowed(screeningId);

        // Then
        assertTrue(result, "첫 번째 요청은 허용되어야 합니다.");
        verify(rateLimiterPort, times(1)).isAllowed(key, 1, 300);
    }

    @DisplayName("Rate Limiting 통과에 실패한 요청은 차단되어야 한다.")
    @Test
    void givenSecondRequestWithinCooldown_whenCheckingIfAllowed_thenReturnFalse() {
        // Given
        String key = "reservation:" + screeningId;
        when(rateLimiterPort.isAllowed(key, 1, 300)).thenReturn(false);

        // When
        boolean result = movieReservationRateLimiterService.isAllowed(screeningId);

        // Then
        assertFalse(result, "5분 내 중복 요청은 차단되어야 합니다.");
        verify(rateLimiterPort, times(1)).isAllowed(key, 1, 300);
    }

}
