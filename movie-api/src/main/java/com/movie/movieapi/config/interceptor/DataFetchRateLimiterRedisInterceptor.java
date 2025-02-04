package com.movie.movieapi.config.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import com.movie.domain.common.RateLimiterHandler;
import com.movie.movieapi.config.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DataFetchRateLimiterRedisInterceptor implements HandlerInterceptor {

    private final RateLimiterHandler rateLimiterHandler;

    // Guava RateLimiter (IP별 제한)
    private static final Map<String, RateLimiter> ipRateLimiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = WebUtils.getClientIp(request);
        String token = request.getHeader("Authorization");
        String key = "%s:%s".formatted(ip, token);

        boolean isAcquire = rateLimiterHandler.tryAcquire(key);
        if (!isAcquire) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "");
        }

        // Redis에서 차단된 IP 인지 확인
        if (rateLimiterHandler.isIpBlocked(ip)) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "IP is blocked : IP - %s".formatted(ip));
        }

        // Redis에서 요청 횟수 증가 및 초과 체크
        if (rateLimiterHandler.isRequestLimitExceeded(ip)) {
            rateLimiterHandler.blockIp(ip);
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "Request limit exceeded : IP - %s".formatted(ip));
        }

        return true;
    }
}
