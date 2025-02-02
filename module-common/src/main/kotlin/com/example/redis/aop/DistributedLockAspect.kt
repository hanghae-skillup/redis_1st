package com.example.redis.aop

import com.example.redis.annotations.DistributedLock
import com.example.redis.movie.Reservation
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

@Aspect
@Component
class DistributedLockAspect(
    val redissonClient: RedissonClient
) {

    @Around("@annotation(DistributedLock)")
    fun around(joinPoint: ProceedingJoinPoint, lock: DistributedLock): Any? {
        val methodSignature = joinPoint.signature as MethodSignature
        val methodArgs = joinPoint.args

        val lockKey = lock.lockKey
        val dynamicKey = generateDynamicKey(lockKey, methodArgs)
        val waitTime = lock.waitTime
        val leaseTime = lock.leaseTime
        val timeUnit = lock.timeUnit

        val rLock = redissonClient.getLock(dynamicKey)

        if(rLock.tryLock(waitTime, leaseTime, timeUnit)) {
            try {
                return joinPoint.proceed()
            } finally {
                rLock.unlock()
            }
        } else {
            throw IllegalStateException()
        }
    }

    private fun generateDynamicKey(baseKey: String, args: Array<Any?>): String {
        val dynamicParts = args.filterIsInstance<Reservation>() // Reservation 타입만 추출
            .flatMap { reservation -> // 각 Reservation 객체를 처리
                listOf(
                    reservation.screeningId.toString(), // screeningId를 문자열로 변환
                    reservation.seats.map { it.seatRow }.distinct().joinToString("") // 고유한 seatRow 추출
                )
            }
        return "$baseKey:${dynamicParts.joinToString("-")}"
    }


}