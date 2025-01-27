package com.example.app.common.asepct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class RedisLockTransaction {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T execute(Supplier<T> action) {
        return action.get();
    }
}
