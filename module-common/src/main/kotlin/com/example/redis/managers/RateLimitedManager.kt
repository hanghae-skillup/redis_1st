package com.example.redis.managers

import com.google.common.util.concurrent.RateLimiter
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Component
class RateLimitedManager(
    private val limiters: MutableMap<String, RateLimiter> = ConcurrentHashMap()
) {

    fun tryAcquire(methodName: String, ip: String, rate: Double): Boolean {
        val key = "$methodName:$ip"
        val rateLimiter = this.limiters.computeIfAbsent(key) {
            RateLimiter.create(rate)
        }
        return rateLimiter.tryAcquire()
    }
}