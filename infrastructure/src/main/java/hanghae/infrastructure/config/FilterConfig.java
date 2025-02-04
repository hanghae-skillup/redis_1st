package hanghae.infrastructure.config;

import hanghae.infrastructure.adapter.RateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> filterRegistrationBean(RateLimitFilter rateLimitFilter) {
        FilterRegistrationBean<RateLimitFilter> filterBean = new FilterRegistrationBean<>(rateLimitFilter);
        filterBean.addUrlPatterns("/api/v1/movie/*");
        filterBean.setOrder(1);

        return filterBean;
    }
}
