package com.example.app.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key();  // 락 키
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    long leaseTime() default 3L;  // 락이 자동으로 해제될 시간 (초)
    long waitTime() default 2L; // 락을 얻기 위해 대기할 최대 시간 (초)
}