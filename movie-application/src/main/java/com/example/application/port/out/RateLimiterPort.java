package com.example.application.port.out;

public interface RateLimiterPort {
    boolean isAllowed(String key, int maxRequests, int durationInSeconds);
}
