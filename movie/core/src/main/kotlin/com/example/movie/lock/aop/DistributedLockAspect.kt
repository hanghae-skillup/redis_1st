package com.example.movie.lock.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Aspect
@Component
class DistributedLockAspect(
    private val redissonClient: RedissonClient
) {

    @Around("@annotation(distributedLock)")
    fun aroundDistributedLock(joinPoint: ProceedingJoinPoint, distributedLock: DistributedLock): Any? {
        val context = StandardEvaluationContext().apply {
            val paramNames = (joinPoint.signature as MethodSignature).parameterNames
            joinPoint.args.forEachIndexed { index, arg ->
                setVariable(paramNames[index], arg)
            }
        }

        val parser = SpelExpressionParser()
        val lockKey = parser.parseExpression(distributedLock.key).getValue(context, String::class.java)
            ?: throw IllegalArgumentException("Failed to parse distributedLock key")

        val lock = redissonClient.getLock(lockKey)

        return if (lock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, TimeUnit.MILLISECONDS)) {
            try {
                joinPoint.proceed()
            } finally {
                if (lock.isLocked && lock.isHeldByCurrentThread) {
                    lock.unlock()
                }
            }
        } else {
            throw IllegalStateException("Failed to acquire lock key: $lockKey")
        }
    }
}