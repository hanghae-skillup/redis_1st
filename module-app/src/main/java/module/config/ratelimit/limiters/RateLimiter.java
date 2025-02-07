package module.config.ratelimit.limiters;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public interface RateLimiter {
	boolean preCheck(Object[] args, ContentCachingRequestWrapper request);
	default void postCheck(Object[] args, ContentCachingResponseWrapper response){}
}
