package module.config.ratelimit.limiters;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RateLimiterFactory {

	private final ApplicationContext applicationContext;

	public RateLimiter getRateLimiter(Class<? extends RateLimiter> T){
		return applicationContext.getBean(T);
	}

}
