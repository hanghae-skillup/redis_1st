package com.example.application.SeatReservation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key(); // Redis 락 키
    long leaseTime() default 5000; // 락 유지 시간 (밀리초)
    long waitTime() default 1000; // 락 대기 시간 (밀리초)
}