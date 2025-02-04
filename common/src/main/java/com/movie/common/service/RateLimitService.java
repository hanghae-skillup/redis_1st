package com.movie.common.service;

/**
 * Rate limiting service interface for managing request rate limits.
 */
public interface RateLimitService {
    
    /**
     * Check if the IP address has exceeded its rate limit.
     * 
     * @param ip The IP address to check
     * @throws RateLimitExceededException if the IP has exceeded its rate limit
     */
    void checkIpRateLimit(String ip);
    
    /**
     * Check if the user has exceeded their reservation rate limit for the given schedule time.
     * 
     * @param userId The ID of the user making the reservation
     * @param scheduleTime The schedule time for the reservation
     * @throws RateLimitExceededException if the user has exceeded their reservation rate limit
     */
    void checkUserReservationRateLimit(Long userId, String scheduleTime);
} 