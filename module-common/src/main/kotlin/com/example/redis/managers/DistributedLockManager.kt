package com.example.redis.managers

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class DistributedLockManager(
    private val redissonClient: RedissonClient
) {
    fun <T> executeWithLock(
        lockKey: String,
        waitTime: Long = 0L,
        leaseTime: Long = 10L,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        task: () -> T
    ): T {
        val rLock: RLock = redissonClient.getLock(lockKey)
        return if(rLock.tryLock(waitTime, leaseTime, timeUnit)) {
            try {
                task()
            } finally {
                rLock.unlock()
            }
        } else {
            throw IllegalStateException()
        }
    }
}