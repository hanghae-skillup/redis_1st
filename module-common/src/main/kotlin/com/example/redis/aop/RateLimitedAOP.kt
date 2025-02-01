package com.example.redis.aop

import com.example.redis.annotations.RateLimited
import com.example.redis.exceptions.RateLimitedException
import com.example.redis.managers.RateLimitedManager
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class RateLimitedAOP(
    private val rateLimitedManager: RateLimitedManager,
    private val request: HttpServletRequest
) {

    @Around("@annotation(ratedLimitedAOP)")
    fun limitRequest(joinPoint: ProceedingJoinPoint, rateLimited: RateLimited): Any {
        val ip = request.remoteAddr
        val api = joinPoint.signature.toShortString()
        val rate = rateLimited.value

        return if (rateLimitedManager.tryAcquire(api, ip, rate)) {
            joinPoint.proceed()
        } else {
            throw RateLimitedException(api, ip)
        }
    }
}