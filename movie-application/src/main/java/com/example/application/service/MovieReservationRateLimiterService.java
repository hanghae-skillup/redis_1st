package com.example.application.service;

import com.example.application.port.out.RateLimiterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieReservationRateLimiterService {

    private static final int COOLDOWN_MINUTES = 5;
    private final RateLimiterPort rateLimiterPort;

    public boolean isAllowed(Long screeningId, Long memberId) {
        String key = "reservation:" + screeningId + ":" + memberId;
        int maxRequests = 1; // 1회 허용
        int durationInSeconds = COOLDOWN_MINUTES * 60; // 5분 (300초)

        return rateLimiterPort.isAllowed(key, maxRequests, durationInSeconds);
    }

}
