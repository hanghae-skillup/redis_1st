package com.movie.api.config;

import com.movie.common.service.RateLimitService;
import com.movie.infra.ratelimit.TestRateLimitService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public RateLimitService rateLimitService() {
        return new TestRateLimitService();
    }
} 