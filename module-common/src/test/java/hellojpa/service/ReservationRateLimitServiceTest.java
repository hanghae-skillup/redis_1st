package hellojpa.service;

import hellojpa.exception.RateLimitExceedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRateLimitServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private RScript rScript;

    @InjectMocks
    private ReservationRateLimitService reservationRateLimitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redissonClient.getScript()).thenReturn(rScript);
    }

    @Test
    void enforceRateLimit_WhenFirstRequest_ShouldNotThrowException() {
        // given
        long userId = 1L;
        long screeningId = 1L;

        // TTL이 0이면 최초 요청
        when(rScript.eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        )).thenReturn(0L);

        // when & then
        assertDoesNotThrow(() ->
                reservationRateLimitService.enforceRateLimit(userId, screeningId)
        );

        verify(rScript, times(1)).eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        );
    }

    @Test
    void enforceRateLimit_WhenRateLimitExceeded_ShouldThrowException() {
        // given
        long userId = 1L;
        long screeningId = 1L;

        // TTL이 양수면 이미 요청이 존재
        when(rScript.eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        )).thenReturn(1L);

        // when & then
        RateLimitExceedException exception = assertThrows(
                RateLimitExceedException.class,
                () -> reservationRateLimitService.enforceRateLimit(userId, screeningId)
        );

        assertEquals(
                "같은 시간대의 영화는 5분에 1번만 예약할 수 있습니다.",
                exception.getMessage()
        );

        verify(rScript, times(1)).eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        );
    }

    @Test
    void enforceRateLimit_WithDifferentScreeningId_ShouldNotThrowException() {
        // given
        long userId = 1L;
        long screeningId1 = 1L;
        long screeningId2 = 2L;

        when(rScript.eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        )).thenReturn(0L);

        // when & then
        assertDoesNotThrow(() -> {
            reservationRateLimitService.enforceRateLimit(userId, screeningId1);
            reservationRateLimitService.enforceRateLimit(userId, screeningId2);
        });

        verify(rScript, times(2)).eval(
                eq(RScript.Mode.READ_WRITE),
                anyString(),
                eq(RScript.ReturnType.INTEGER),
                anyList()
        );
    }
}