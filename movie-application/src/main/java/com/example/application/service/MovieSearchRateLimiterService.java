package com.example.application.service;

import com.example.application.port.out.RateLimiterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieSearchRateLimiterService {

    private static final int MAX_REQUESTS_PER_MINUTE = 50;
    private final RateLimiterPort rateLimiterPort;

    public boolean isAllowed(String ip) {
        String key = "search:" + ip;
        int durationInSeconds = 60; // 1분 (60초)

        return rateLimiterPort.isAllowed(key, MAX_REQUESTS_PER_MINUTE, durationInSeconds);
    }

}
