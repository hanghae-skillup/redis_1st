package com.movie.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.service.RateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int TOO_MANY_REQUESTS = 429;
    private final RateLimitService rateLimitService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = getClientIp(request);
        
        try {
            // 조회 API에 대한 IP 기반 rate limit 체크
            if (isQueryRequest(request)) {
                rateLimitService.checkIpRateLimit(clientIp);
            }
            
            // 예약 API에 대한 사용자 기반 rate limit 체크
            if (isReservationRequest(request)) {
                String scheduleTime = request.getParameter("scheduleTime");
                Long userId = getUserIdFromRequest(request);
                if (userId != null && scheduleTime != null) {
                    rateLimitService.checkUserReservationRateLimit(userId, scheduleTime);
                }
            }
            
            return true;
        } catch (RateLimitExceededException e) {
            response.setStatus(TOO_MANY_REQUESTS);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
            return false;
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private boolean isQueryRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/movies") && request.getMethod().equals("GET");
    }

    private boolean isReservationRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/reservations") && request.getMethod().equals("POST");
    }

    private Long getUserIdFromRequest(HttpServletRequest request) {
        // 실제 구현에서는 JWT 토큰이나 세션에서 사용자 ID를 추출
        // 여기서는 임시로 헤더에서 추출
        String userIdStr = request.getHeader("X-User-Id");
        return userIdStr != null ? Long.parseLong(userIdStr) : null;
    }
} 