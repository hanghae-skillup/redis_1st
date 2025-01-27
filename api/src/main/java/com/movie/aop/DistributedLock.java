package com.movie.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    /**
     * 락의 이름
     */
    String key();

    /**
     * 락의 시도 획득 시간 (default = 5초)
     */
    long waitTime() default 5L;

    /**
     * 락의 만료 시간 (default = 3초)
     */
    long leaseTime() default 3L;

    /**
     * 시간 단위 (default = SECONDS)
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
} 