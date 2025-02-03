package hellojpa.config;

import hellojpa.interceptor.RateLimitInterceptor;
import hellojpa.interceptor.ReservationRateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    @Autowired
    private ReservationRateLimitInterceptor reservationRateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/screening/movies");

        registry.addInterceptor(reservationRateLimitInterceptor)
                .addPathPatterns("/reservation/movie");
    }
}
