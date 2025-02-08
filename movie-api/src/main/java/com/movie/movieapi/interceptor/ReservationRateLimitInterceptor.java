package com.movie.movieapi.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import com.movie.domain.movie.ReservationRedisRepository;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.domain.userAccount.UserAccount;
import com.movie.domain.userAccount.UserAccountRepository;
import com.movie.movieapi.config.RequestWrapper;
import com.movie.movieapi.config.WebUtils;
import com.movie.movieapi.interfaces.movie.dto.ReservationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ReservationRateLimitInterceptor implements HandlerInterceptor {

    private static final double REQUESTS_PER_SECOND = 2.0;

    private final UserAccountRepository userAccountRepository;
    private final ReservationRedisRepository reservationRedisRepository;
    private final ObjectMapper objectMapper;

    // Guava RateLimiter (IP별 제한)
    private static final Map<String, RateLimiter> ipRateLimiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = WebUtils.getClientIp(request);

        // 요청 제한 (1초에 2번 이상 초과시)
        RateLimiter rateLimiter = ipRateLimiters.computeIfAbsent(ip, k -> RateLimiter.create(REQUESTS_PER_SECOND));
        if (!rateLimiter.tryAcquire()) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "Too many requests : IP - %s".formatted(ip));
        }

        RequestWrapper requestWrapper = new RequestWrapper(request);
        String requestBody = requestWrapper.getBodyContent();
        ReservationDto.Reserve reserve = objectMapper.readValue(requestBody, ReservationDto.Reserve.class);

        // scheduleId 추출
        Long scheduleId = reserve.scheduleId();
        if (scheduleId == null) {
            throw new ApplicationException(ErrorCode.CONTENT_NOT_FOUND, "schedule id not found");
        }

        // token 헤더 추출
        String token = request.getHeader("Authorization");
        UserAccount userAccount = userAccountRepository.getUserAccountByToken(token);

        ReservationCommand.GetCache getCache = ReservationCommand.GetCache.of(scheduleId, userAccount.getId());
        Long reservedAtTimeMillis = reservationRedisRepository.getReservedAtTimeMillis(getCache);
        if (reservedAtTimeMillis != null) {
            return false;
        }

        return true;
    }
}
