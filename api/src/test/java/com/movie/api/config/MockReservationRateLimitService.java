package com.movie.api.config;

import com.movie.infra.ratelimit.ReservationRateLimitService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("test")
public class MockReservationRateLimitService extends ReservationRateLimitService {
    
    public MockReservationRateLimitService() {
        super(null, null); // Redis 관련 의존성 없이 생성
    }

    @Override
    public boolean canBook(String userId, String scheduleId) {
        return true; // 테스트에서는 항상 rate limit 통과
    }
} 