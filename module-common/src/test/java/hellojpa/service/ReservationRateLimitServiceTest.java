package hellojpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRateLimitServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RScript script;

    @InjectMocks
    private ReservationRateLimitService reservationRateLimitService;

    @BeforeEach
    void setUp() {
        // Mockito 초기화
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsAllowed_whenKeyDoesNotExist_shouldReturnTrue() {
        // given
        Long userId = 1L;
        Long screeningId = 1L;
        String key = String.format("rate_limit:%d:%d", userId, screeningId);

        // Mocking RedissonClient와 RScript
        when(redissonClient.getScript()).thenReturn(script);
        when(script.eval(eq(RScript.Mode.READ_WRITE), anyString(), eq(RScript.ReturnType.INTEGER), anyList(), any())).thenReturn(1L);

        // when
        boolean result = reservationRateLimitService.isAllowed(userId, screeningId);

        // then
        assertTrue(result);
        verify(script, times(1)).eval(eq(RScript.Mode.READ_WRITE), anyString(), eq(RScript.ReturnType.INTEGER), anyList(), any());
    }

    @Test
    void testIsAllowed_whenKeyExists_shouldReturnFalse() {
        // given
        Long userId = 1L;
        Long screeningId = 101L;
        String key = String.format("rate_limit:%d:%d", userId, screeningId);

        // Mocking RedissonClient와 RScript
        when(redissonClient.getScript()).thenReturn(script);
        when(script.eval(eq(RScript.Mode.READ_WRITE), anyString(), eq(RScript.ReturnType.INTEGER), anyList(), any())).thenReturn(0L);

        // when
        boolean result = reservationRateLimitService.isAllowed(userId, screeningId);

        // then
        assertFalse(result);
        verify(script, times(1)).eval(eq(RScript.Mode.READ_WRITE), anyString(), eq(RScript.ReturnType.INTEGER), anyList(), any());
    }
}