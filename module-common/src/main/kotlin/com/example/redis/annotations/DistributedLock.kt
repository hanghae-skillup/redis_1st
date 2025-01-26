package com.example.redis.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DistributedLock(
    val lockKey: String,
    val waitTime: Long = 1,
    val leaseTime: Long = 10
)
