package com.movie.api.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimitConfig {

    @Bean
    public LoadingCache<String, RateLimiter> ipRateLimitCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build(new CacheLoader<>() {
                    @Override
                    public RateLimiter load(String key) {
                        // 1분당 50회 요청 허용
                        return RateLimiter.create(50.0 / 60.0);
                    }
                });
    }

    @Bean
    public LoadingCache<String, RateLimiter> userReservationRateLimitCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public RateLimiter load(String key) {
                        // 5분에 1회 예약 허용
                        return RateLimiter.create(1.0 / 300.0);
                    }
                });
    }
} 