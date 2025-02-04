package com.movie.infra.ratelimit;

import com.movie.infra.common.response.ApiResponse;
import com.movie.infra.common.response.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.context.annotation.Profile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final RateLimitService rateLimitService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        
        if (!rateLimitService.tryAcquire(ip)) {
            response.setStatus(ErrorCode.RATE_LIMIT_EXCEEDED.getStatus().value());
            response.setContentType("application/json");
            
            ApiResponse<?> errorResponse = ApiResponse.error(
                ErrorCode.RATE_LIMIT_EXCEEDED.getStatus().value(),
                ErrorCode.RATE_LIMIT_EXCEEDED.getMessage()
            );
            
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return false;
        }
        return true;
    }
} 