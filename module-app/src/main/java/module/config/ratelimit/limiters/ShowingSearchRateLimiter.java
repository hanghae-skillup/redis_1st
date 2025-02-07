package module.config.ratelimit.limiters;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;

import exception.showing.TooManyRequestException;

@Component
public class ShowingSearchRateLimiter implements module.config.ratelimit.limiters.RateLimiter {

	private static final double TOKEN_PER_SECOND = 2;
	private static final int LIMIT_PER_MINUTE = 50;

	private final Cache<String, Boolean> blockedIp = CacheBuilder.newBuilder()
		.expireAfterWrite(1, TimeUnit.HOURS)
		.build();

	private final Cache<String, Integer> requestCount = CacheBuilder.newBuilder()
		.expireAfterWrite(1, TimeUnit.MINUTES)
		.build();

	private final Cache<String, com.google.common.util.concurrent.RateLimiter> ipRateLimiter = CacheBuilder.newBuilder()
		.expireAfterAccess(1, TimeUnit.MINUTES)
		.build();

	@Override
	public boolean preCheck(Object[] args, ContentCachingRequestWrapper request){
		String ip = request.getRemoteAddr();
		increaseRequestCount(ip);
		return isAllowed(ip);
	}

	public boolean isAllowed(String ip){
		int count = requestCount.getIfPresent(ip);
		System.out.println(count + "회 요청");

		if(count > LIMIT_PER_MINUTE){
			addBlock(ip);
			throw new TooManyRequestException();
		}

		if(isBlocked(ip)){
			throw new TooManyRequestException();
		}

		RateLimiter rateLimiter = getRateLimiter(ip);
		if(!rateLimiter.tryAcquire()){
			throw new TooManyRequestException();
		}

		return true;
	}

	public RateLimiter getRateLimiter(String ip){
		RateLimiter rateLimiter = ipRateLimiter.getIfPresent(ip);

		if(rateLimiter == null){
			rateLimiter = com.google.common.util.concurrent.RateLimiter.create(TOKEN_PER_SECOND);
			ipRateLimiter.put(ip,rateLimiter);
		}

		return rateLimiter;
	}

	public void increaseRequestCount(String ip){
		Integer count = requestCount.getIfPresent(ip);

		if(count == null) {
			count = 0;
		}

		// count 증가
		requestCount.put(ip, ++count);
	}

	public boolean isBlocked(String ip) {
		return blockedIp.getIfPresent(ip) != null;
	}

	public void addBlock(String ip) {
		blockedIp.put(ip, true);
	}
}
