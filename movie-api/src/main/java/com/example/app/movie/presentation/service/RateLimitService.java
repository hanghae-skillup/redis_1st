package com.example.app.movie.presentation.service;

import com.example.app.common.exception.RateLimitException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("UnstableApiUsage")
@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final Cache<String, RateLimiter> rateLimiters = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS)
            .build();

    private final Cache<String, LocalDateTime> blockedIps = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    private final Cache<String, AtomicInteger> requestCounts = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    public void checkAccessLimit(String clientIp) throws ExecutionException {
        var isBlocked = blockedIps.getIfPresent(clientIp) != null;

        if (isBlocked) {
            throw new RateLimitException();
        }

        RateLimiter rateLimiter = rateLimiters.get(clientIp, () -> RateLimiter.create(1.0));

        AtomicInteger count = requestCounts.get(clientIp, () -> new AtomicInteger(0));
        int currentCount = count.incrementAndGet();

        if (currentCount >= 50) {
            blockedIps.put(clientIp, LocalDateTime.now());
            throw new RateLimitException();
        }

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitException();
        }
    }
}
