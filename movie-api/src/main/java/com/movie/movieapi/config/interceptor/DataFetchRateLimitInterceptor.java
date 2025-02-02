package com.movie.movieapi.config.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
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
    private static final double REQUEST_LIMIT = 50.0;
    private static final int BLOCK_DURATION_HOURS = 1;
    private static final Map<String, AccessInfo> ipAccessMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ip = getClientIp(request);
        long currentTime = System.currentTimeMillis();

        // 만료된 IP 정보 제거
        ipAccessMap.entrySet().removeIf(entry -> entry.getValue().isExpired(currentTime));

        // 요청한 IP의 AccessInfo 객체 조회
        AccessInfo accessInfo = ipAccessMap.computeIfAbsent(ip, k -> new AccessInfo());

        // 차단된 IP 라면 예외 발생
        if (accessInfo.isBlocked(currentTime)) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "IP is blocked : IP - %s".formatted(ip));
        }

        // 해당하는 IP의 요청 초과횟수 확인
        if (!accessInfo.tryAcquire()) {
            accessInfo.block(currentTime);
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "Request limit exceeded : IP - %s".formatted(ip));
        }

        return true;
    }

    private static class AccessInfo {
        private final RateLimiter rateLimiter;
        private long blockUntil;

        public AccessInfo() {
            this.rateLimiter = RateLimiter.create(REQUEST_LIMIT); // 1분에 50번의 request 허용
            this.blockUntil = 0;
        }

        public boolean tryAcquire() {
            return rateLimiter.tryAcquire(1, 20, TimeUnit.MILLISECONDS);
        }

        public void block(long currentTime) {
            this.blockUntil = currentTime + TimeUnit.HOURS.toMillis(BLOCK_DURATION_HOURS);
        }

        public boolean isBlocked(long currentTime) {
            return currentTime < blockUntil;
        }

        public boolean isExpired(long currentTime) {
            return blockUntil > 0 && currentTime > blockUntil;
        }
    }

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
