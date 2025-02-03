package com.movie.infra.ratelimit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final RateLimitService rateLimitService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        
        if (!rateLimitService.tryAcquire(ip)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 429);
            errorResponse.put("message", "Too Many Requests - Rate limit exceeded");
            
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return false;
        }
        return true;
    }
} 