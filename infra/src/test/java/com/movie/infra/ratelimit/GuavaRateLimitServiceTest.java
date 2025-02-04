package com.movie.infra.ratelimit;

import com.movie.common.exception.RateLimitExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GuavaRateLimitServiceTest {

    private GuavaRateLimitService rateLimitService;

    @BeforeEach
    void setUp() {
        rateLimitService = new GuavaRateLimitService();
    }

    @Test
    @DisplayName("IP 기반 Rate Limit - 정상 요청")
    void checkIpRateLimit_Success() {
        // given
        String ip = "127.0.0.1";

        // when & then
        rateLimitService.checkIpRateLimit(ip); // 예외가 발생하지 않아야 함
    }

    @Test
    @DisplayName("IP 기반 Rate Limit - 제한 초과")
    void checkIpRateLimit_ExceedsLimit() {
        // given
        String ip = "127.0.0.1";

        // when & then
        for (int i = 0; i < 100; i++) {
            try {
                rateLimitService.checkIpRateLimit(ip);
            } catch (RateLimitExceededException e) {
                // 예외가 발생하면 정상
                return;
            }
        }

        throw new AssertionError("Rate limit exceeded exception should have been thrown");
    }

    @Test
    @DisplayName("사용자 예약 Rate Limit - 정상 요청")
    void checkUserReservationRateLimit_Success() {
        // given
        Long userId = 1L;
        String scheduleTime = "2024-01-01T10:00:00";

        // when & then
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime); // 예외가 발생하지 않아야 함
    }

    @Test
    @DisplayName("사용자 예약 Rate Limit - 제한 초과")
    void checkUserReservationRateLimit_ExceedsLimit() {
        // given
        Long userId = 1L;
        String scheduleTime = "2024-01-01T10:00:00";

        // when & then
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime);

        assertThatThrownBy(() -> rateLimitService.checkUserReservationRateLimit(userId, scheduleTime))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("예매는 5분에 한 번만 시도할 수 있습니다");
    }

    @Test
    @DisplayName("다른 시간대의 영화는 Rate Limit에 영향을 받지 않음")
    void checkUserReservationRateLimit_DifferentSchedule() {
        // given
        Long userId = 1L;
        String scheduleTime1 = "2024-01-01T10:00:00";
        String scheduleTime2 = "2024-01-01T13:00:00";

        // when & then
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime1);
        rateLimitService.checkUserReservationRateLimit(userId, scheduleTime2); // 예외가 발생하지 않아야 함
    }
} 