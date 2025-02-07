package module.config.ratelimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import module.config.ratelimit.limiters.RateLimiter;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitWith {
	Class<? extends RateLimiter> rateLimiter();
	boolean postProcess() default false;
	boolean preProcess() default true;
}
