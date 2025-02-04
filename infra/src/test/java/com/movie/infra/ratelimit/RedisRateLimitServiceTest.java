package com.movie.infra.ratelimit;

import com.movie.common.exception.RateLimitExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RedisRateLimitServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private RRateLimiter rateLimiter;

    private RedisRateLimitService rateLimitService;

    @BeforeEach
    void setUp() {
        rateLimitService = new RedisRateLimitService(redissonClient, redisTemplate);
    }

    @Test
    @DisplayName("IP 기반 Rate Limit - 정상 요청")
    void checkIpRateLimit_Success() {
        // given
        String ip = "127.0.0.1";
        given(redisTemplate.hasKey(anyString())).willReturn(false);
        given(redissonClient.getRateLimiter(anyString())).willReturn(rateLimiter);
        given(rateLimiter.tryAcquire()).willReturn(true);

        // when & then
        rateLimitService.checkIpRateLimit(ip); // 예외가 발생하지 않아야 함
    }

    @Test
    @DisplayName("IP 기반 Rate Limit - IP 차단됨")
    void checkIpRateLimit_IpBanned() {
        // given
        String ip = "127.0.0.1";
        given(redisTemplate.hasKey(anyString())).willReturn(true);

        // when & then
        assertThatThrownBy(() -> rateLimitService.checkIpRateLimit(ip))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("IP가 차단되었습니다");
    }

    @Test
    @DisplayName("IP 기반 Rate Limit - 제한 초과")
    void checkIpRateLimit_ExceedsLimit() {
        // given
        String ip = "127.0.0.1";
        ValueOperations<String, String> valueOps = mock(ValueOperations.class);
        
        given(redisTemplate.hasKey(anyString())).willReturn(false);
        given(redisTemplate.opsForValue()).willReturn(valueOps);
        given(redissonClient.getRateLimiter(anyString())).willReturn(rateLimiter);
        given(rateLimiter.tryAcquire()).willReturn(false);

        // when & then
        assertThatThrownBy(() -> rateLimitService.checkIpRateLimit(ip))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("너무 많은 요청을 보냈습니다");

        verify(valueOps).set(anyString(), eq("banned"), any());
    }

    @Test
    @DisplayName("사용자 예약 Rate Limit - 정상 요청")
    void checkUserReservationRateLimit_Success() {
        // given
        Long userId = 1L;
        String scheduleTime = "2024-01-01T10:00:00";
        given(redissonClient.getRateLimiter(anyString())).willReturn(rateLimiter);
        given(rateLimiter.tryAcquire()).willReturn(true);

        // when & then
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime); // 예외가 발생하지 않아야 함
    }

    @Test
    @DisplayName("사용자 예약 Rate Limit - 제한 초과")
    void checkUserReservationRateLimit_ExceedsLimit() {
        // given
        Long userId = 1L;
        String scheduleTime = "2024-01-01T10:00:00";
        given(redissonClient.getRateLimiter(anyString())).willReturn(rateLimiter);
        given(rateLimiter.tryAcquire()).willReturn(false);

        // when & then
        assertThatThrownBy(() -> rateLimitService.checkUserReservationRateLimit(userId, scheduleTime))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("예매는 5분에 한 번만 시도할 수 있습니다");
    }
} 