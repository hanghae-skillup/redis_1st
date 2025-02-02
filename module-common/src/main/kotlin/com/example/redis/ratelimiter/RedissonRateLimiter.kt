package com.example.redis.ratelimiter

import org.redisson.api.RRateLimiter
import org.redisson.api.RateIntervalUnit
import org.redisson.api.RateType
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

@Component("redissonRateLimiter")
class RedissonRateLimiter(
    private val redissonClient: RedissonClient
): RateLimiter {
    override fun tryAcquire(api: String, ip: String, rate: Long): Boolean {
        val key = "$api:$ip"
        val rateLimiter: RRateLimiter = redissonClient.getRateLimiter(key)

        if(!rateLimiter.isExists) {
            rateLimiter.trySetRate(RateType.OVERALL, rate, 1, RateIntervalUnit.MINUTES)
        }

        return rateLimiter.tryAcquire()
    }
}