package com.movie.common.service;

public interface RateLimitService {
    boolean checkIpRateLimit(String ip);
    void checkUserReservationRateLimit(Long userId, String scheduleTime);
} 