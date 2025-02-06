package com.example.application.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key();
    long waitTime() default 2_000;   // 적당한 대기 시간을 고려해 잠금 시간의 2배 정도로 설정
    long leaseTime() default 1_000; // 지연을 고려해 트랜잭션 평균 실행 시간의 2배 정도로 설정
}

