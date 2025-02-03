package com.example.aop;

import com.example.config.ratelimit.RateLimit;
import com.example.exception.BusinessError;
import com.example.reservation.request.ReservationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final RateLimit rateLimit;

    @Around("@annotation(movieSearchRateLimited)")
    public Object around(ProceedingJoinPoint joinPoint, MovieSearchRateLimited movieSearchRateLimited) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        if (!rateLimit.isMovieSearchAllowed(ip)) {
            throw BusinessError.MOVIE_SEARCH_MAX_FIND_ERROR.exception();
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(reservationRateLimited)")
    public Object around(ProceedingJoinPoint joinPoint, ReservationRateLimited reservationRateLimited) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof com.example.reservation.request.ReservationRequest) {
                ReservationRequest request = (ReservationRequest) arg;
                if (!rateLimit.isReservationAllowed(request.getMemberId(), request.getScreeningId())) {
                    throw BusinessError.RESERVATION_RATE_LIMIT_ERROR.exception();
                }

            }
        }

        return joinPoint.proceed();
    }
}
