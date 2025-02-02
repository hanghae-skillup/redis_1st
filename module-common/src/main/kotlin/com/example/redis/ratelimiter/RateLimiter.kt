package com.example.redis.ratelimiter

interface RateLimiter {
    fun tryAcquire(api: String, ip: String, rate: Long): Boolean
}