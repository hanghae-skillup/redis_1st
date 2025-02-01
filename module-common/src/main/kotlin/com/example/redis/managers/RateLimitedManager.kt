package com.example.redis.managers

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.Refill
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit

@Component
class RateLimitedManager(
    private val limiters: MutableMap<String, Bucket> = ConcurrentHashMap()
) {

    fun tryAcquire(api: String, ip: String, rate: Long): Boolean {
        val key = "$api:$ip"
        val rateLimiter = this.limiters.computeIfAbsent(key) {
            val refill = Refill.greedy(rate, Duration.ofMinutes(1)) // 1분 동안 15개의 요청 허용
            val limit = Bandwidth.classic(rate, refill)
            Bucket.builder().addLimit(limit).build()
        }
        return rateLimiter.tryConsume(1)
    }
}