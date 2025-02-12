package hellojpa.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import hellojpa.dto.RateLimitResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_MINUTE = 50;  // 50 requests per minute
    private static final int BLOCK_HOURS = 1;  // Block for 1 hour
    private static final double REQUESTS_PER_MINUTE = 50.0 / 60.0;  // RateLimiter value for 50 requests per minute

    // IP별 요청 횟수를 저장
    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    // IP별 차단 시간을 저장
    private final Map<String, LocalDateTime> blockedIps = new ConcurrentHashMap<>();
    // IP별 RateLimiter를 저장
    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        // 차단된 IP인지 확인
        if (isBlocked(clientIp, now)) {
            sendRateLimitResponse(response);
            return false;
        }

        // IP별 RateLimiter 가져오기 (없으면 새로 생성)
        RateLimiter rateLimiter = rateLimiters.computeIfAbsent(clientIp,
                k -> RateLimiter.create(REQUESTS_PER_MINUTE));

        // IP별 RateLimiter로 요청 제한
        if (!rateLimiter.tryAcquire()) {
            sendRateLimitResponse(response);
            return false;
        }

        // 요청 횟수 카운트
        requestCounts.computeIfAbsent(clientIp, k -> new AtomicInteger(0)).incrementAndGet();

        // IP가 1분 내에 50회 이상 요청하면 차단
        if (requestCounts.get(clientIp).get() > MAX_REQUESTS_PER_MINUTE) {
            blockedIps.put(clientIp, now.plusHours(BLOCK_HOURS));  // 1시간 동안 차단
            rateLimiters.remove(clientIp);  // RateLimiter 제거
            sendRateLimitResponse(response);
            return false;
        }

        return true;
    }

    private boolean isBlocked(String clientIp, LocalDateTime now) {
        LocalDateTime blockUntil = blockedIps.get(clientIp);
        if (blockUntil != null) {
            if (now.isAfter(blockUntil)) {
                // 차단 시간이 지났으면 차단 해제
                blockedIps.remove(clientIp);
                requestCounts.put(clientIp, new AtomicInteger(0));  // 요청 횟수 초기화
                rateLimiters.remove(clientIp);  // RateLimiter 초기화
                return false;
            }
            return true;
        }
        return false;
    }

    private void sendRateLimitResponse(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String rateLimitExceeded = objectMapper.writeValueAsString(new RateLimitResponseDto<>(
                429,
                "RATE_LIMIT_EXCEEDED",
                "요청 제한 횟수를 초과했습니다.",
                null)
        );

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(rateLimitExceeded);
    }
}