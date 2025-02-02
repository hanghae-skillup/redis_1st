package com.example.redis.aop

import com.example.redis.annotations.RateLimited
import com.example.redis.exceptions.RateLimitedException
import com.example.redis.ratelimiter.RateLimiter
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Aspect
class RateLimitedAspect(
    @Qualifier("redissonRateLimiter") private val rateLimiter: RateLimiter,
    private val request: HttpServletRequest
) {

    @Around("@annotation(rateLimited)")
    fun limitRequest(joinPoint: ProceedingJoinPoint, rateLimited: RateLimited): Any {
        val ip = request.remoteAddr
        val api = request.requestURI
        val rate = rateLimited.value

        return if (rateLimiter.tryAcquire(api, ip, rate)) {
            joinPoint.proceed()
        } else {
            throw RateLimitedException(api, ip)
        }
    }
}