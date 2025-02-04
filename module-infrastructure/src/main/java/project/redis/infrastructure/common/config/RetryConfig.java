package project.redis.infrastructure.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;


@Configuration
@EnableRetry
public class RetryConfig {

    @Bean("reservationSeatRetryTemplate")
    public RetryTemplate reservationSeatRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(2));

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(50);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        return retryTemplate;
    }
}
