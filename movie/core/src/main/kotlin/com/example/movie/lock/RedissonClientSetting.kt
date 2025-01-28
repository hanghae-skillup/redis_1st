package com.example.movie.lock

import org.redisson.api.RedissonClient
import java.util.concurrent.TimeUnit

fun <T> RedissonClient.withLock(
    lockKey: String,
    waitTime: Long = 0,
    leaseTime: Long = 0,
    block: () -> T
): T {
    val lock = this.getLock(lockKey)
    if (waitTime > 0 && leaseTime > 0) {
        if (!lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS)) {
            throw IllegalStateException("Failed to acquire lock key: $lockKey")
        }
    } else {
        lock.lock()
    }

    return try {
        block()
    } finally {
        if (lock.isHeldByCurrentThread) {
            lock.unlock()
        }
    }
}