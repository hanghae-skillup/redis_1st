package com.movie.movieapi.config;

import com.movie.movieapi.config.interceptor.DataFetchRateLimitInterceptor;
import com.movie.movieapi.config.interceptor.DataFetchRateLimiterRedisInterceptor;
import com.movie.movieapi.config.interceptor.ReservationRateLimitInterceptor;
import com.movie.movieapi.config.interceptor.ReservationRateLimitRedisInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final DataFetchRateLimitInterceptor dataFetchRateLimitInterceptor;
    private final ReservationRateLimitInterceptor reservationRateLimitInterceptor;

    private final DataFetchRateLimiterRedisInterceptor dataFetchRateLimiterRedisInterceptor;
    private final ReservationRateLimitRedisInterceptor reservationRateLimitRedisInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataFetchRateLimiterRedisInterceptor)
                .addPathPatterns("/api/movies/**", "/api/schedules/**");

        registry.addInterceptor(reservationRateLimitRedisInterceptor)
                .addPathPatterns("/api/reservations/**");
    }
}
