package com.movie.movieapi.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import com.movie.domain.common.RateLimiterHandler;
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

@Component
@RequiredArgsConstructor
public class ReservationRateLimitRedisInterceptor implements HandlerInterceptor {

    private final RateLimiterHandler rateLimiterHandler;

    private final UserAccountRepository userAccountRepository;
    private final ReservationRedisRepository reservationRedisRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = WebUtils.getClientIp(request);
        String token = request.getHeader("Authorization");
        String key = "%s:%s".formatted(ip, token);

        boolean isAcquire = rateLimiterHandler.tryAcquire(key);
        if (!isAcquire) {
            throw new ApplicationException(ErrorCode.TOO_MANY_REQUESTS, "");
        }

        if (!(request instanceof RequestWrapper)) {
            request = new RequestWrapper(request); // 래핑된 요청을 생성
        }

        String requestBody = ((RequestWrapper) request).getBodyContent();
        ReservationDto.Reserve reserve = objectMapper.readValue(requestBody, ReservationDto.Reserve.class);

        // scheduleId 추출
        Long scheduleId = reserve.scheduleId();
        if (scheduleId == null) {
            throw new ApplicationException(ErrorCode.CONTENT_NOT_FOUND, "schedule id not found");
        }

        // token 헤더 추출
        UserAccount userAccount = userAccountRepository.getUserAccountByToken(token);

        ReservationCommand.GetCache getCache = ReservationCommand.GetCache.of(scheduleId, userAccount.getId());
        Long reservedAtTimeMillis = reservationRedisRepository.getReservedAtTimeMillis(getCache);
        if (reservedAtTimeMillis != null) {
            return false;
        }

        return true;
    }
}
