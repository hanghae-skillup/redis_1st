package com.movie.movieapi.config;

import com.movie.movieapi.filter.RequestWrapperFilter;
import com.movie.movieapi.interceptor.DataFetchRateLimiterRedisInterceptor;
import com.movie.movieapi.interceptor.ReservationRateLimitRedisInterceptor;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final DataFetchRateLimiterRedisInterceptor dataFetchRateLimiterRedisInterceptor;
    private final ReservationRateLimitRedisInterceptor reservationRateLimitRedisInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataFetchRateLimiterRedisInterceptor)
                .addPathPatterns("/api/movies/**", "/api/schedules/**");

        registry.addInterceptor(reservationRateLimitRedisInterceptor)
                .addPathPatterns("/api/reservations/**");
    }

    @Bean
    public FilterRegistrationBean<Filter> requestWrapperFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RequestWrapperFilter());
        filterRegistrationBean.addUrlPatterns("/api/reservations/*");
        return filterRegistrationBean;
    }
}
