package com.movie.infra.ratelimit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.service.RateLimitService;
import com.movie.infra.common.response.ApiResponse;
import com.movie.infra.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.context.annotation.Profile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private static final int TOO_MANY_REQUESTS = 429;
    private final RateLimitService rateLimitService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        try {
            if (!rateLimitService.checkIpRateLimit(ip)) {
                response.setStatus(TOO_MANY_REQUESTS);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Rate limit exceeded");
                objectMapper.writeValue(response.getWriter(), errorResponse);
                return false;
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
} 