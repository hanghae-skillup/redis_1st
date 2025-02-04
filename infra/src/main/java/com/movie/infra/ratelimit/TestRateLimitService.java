package com.movie.infra.ratelimit;

import com.movie.common.service.RateLimitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestRateLimitService implements RateLimitService {
    @Override
    public void checkIpRateLimit(String ip) {
        // 테스트 환경에서는 rate limit을 적용하지 않음
    }

    @Override
    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        // 테스트 환경에서는 rate limit을 적용하지 않음
    }
} 