package com.example.redis.annotations

import java.util.concurrent.TimeUnit

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DistributedLock(
    val lockKey: String,
    val waitTime: Long = 1,
    val leaseTime: Long = 10,
    val timeUnit: TimeUnit = TimeUnit.SECONDS
)
