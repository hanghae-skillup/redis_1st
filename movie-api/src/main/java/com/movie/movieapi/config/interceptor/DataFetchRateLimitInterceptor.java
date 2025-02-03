package com.movie.movieapi.config.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import com.movie.domain.common.RateLimitRedisRepository;
import com.movie.movieapi.config.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DataFetchRateLimitInterceptor implements HandlerInterceptor {

    private static final double REQUESTS_PER_SECOND = 2.0;
    private final RateLimitRedisRepository rateLimitRedisRepository;

    // Guava RateLimiter (IP별 제한)
    private static final Map<String, RateLimiter> ipRateLimiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ip = WebUtils.getClientIp(request);

        // Redis에서 차단된 IP 인지 확인
        if (rateLimitRedisRepository.isIpBlocked(ip)) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "IP is blocked : IP - %s".formatted(ip));
        }

        // 요청 제한 (1초에 2번 이상 초과시)
        RateLimiter rateLimiter = ipRateLimiters.computeIfAbsent(ip, k -> RateLimiter.create(REQUESTS_PER_SECOND));
        if (!rateLimiter.tryAcquire()) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "Too many requests : IP - %s".formatted(ip));
        }

        // 3. Redis에서 요청 횟수 증가 및 초과 체크
        if (rateLimitRedisRepository.isRequestLimitExceeded(ip)) {
            rateLimitRedisRepository.blockIp(ip);
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "Request limit exceeded : IP - %s".formatted(ip));
        }

        return true;
    }


}
