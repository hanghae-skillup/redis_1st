package hanghae.infrastructure.adapter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Order(1)
@Component
@RequiredArgsConstructor
public class RateLimitFilter implements Filter {

    private final RedissonClient redissonClient;

    private static final String IP_BLOCKED_MAP = "ip_blocked_map";

    private static final long MAX_REQUEST_PER_MINUTES = 50;
    private static final long BLOCKED_DURATION_SECONDS = 3600;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String clientIp = getClientIp(request);

        if (isBlocked(clientIp)) {
            setStatusTo429(response);
            setBlockResult(response);
            setRemainBlockedTime(response, clientIp);
            return;
        }

        if (isExceededMaxRequestCount(clientIp)) {
            blockIp(clientIp);
            setStatusTo429(response);
            setBlockResult(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void setStatusTo429(HttpServletResponse response) {
        response.setStatus(429); // TOO_MANY_REQUESTS
    }

    private static void setBlockResult(HttpServletResponse response) throws IOException {
        response.getWriter().write("IP blocked for 1 hour.");
    }

    private void setRemainBlockedTime(HttpServletResponse response, String clientIp) throws IOException {
        long ttl = getBlockedMap().remainTimeToLive(clientIp) / 1000;
        response.getWriter().write("\nRemaining blocked time: " + ttl + "s");
    }

    private String getClientIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    private boolean isBlocked(String clientIp) {
        return redissonClient.getMapCache(IP_BLOCKED_MAP).containsKey(clientIp);
    }

    private boolean isExceededMaxRequestCount(String clientIp) {
        RAtomicLong counter = redissonClient.getAtomicLong(clientIp);

        long currentCounter = counter.incrementAndGet();

        if (counter.get() == 1) {
            counter.expire(Duration.ofMinutes(1));
        }

        return currentCounter > MAX_REQUEST_PER_MINUTES;
    }

    private void blockIp(String clientIp) {
        RMapCache<String, Boolean> blockedMap = getBlockedMap();
        blockedMap.put(clientIp, true, BLOCKED_DURATION_SECONDS, TimeUnit.SECONDS);
    }

    private RMapCache<String, Boolean> getBlockedMap() {
        return redissonClient.getMapCache(IP_BLOCKED_MAP);
    }
}
