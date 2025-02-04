package com.movie.infra.ratelimit;

import com.movie.common.service.RateLimitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestRateLimitService implements RateLimitService {
    @Override
    public boolean checkIpRateLimit(String ip) {
        return true; // 테스트 환경에서는 항상 true 반환
    }

    @Override
    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        // 테스트 환경에서는 아무 동작도 하지 않음
    }
} 