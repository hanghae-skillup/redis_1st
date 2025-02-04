package com.movie.api.config;

import com.movie.domain.aop.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Primary
@Profile("test")
public class MockDistributedLockAop {

    @Around("@annotation(com.movie.domain.aop.DistributedLock)")
    public Object executeWithLock(ProceedingJoinPoint joinPoint) throws Throwable {
        // 테스트 환경에서는 분산 락을 적용하지 않고 바로 실행
        return joinPoint.proceed();
    }
} 