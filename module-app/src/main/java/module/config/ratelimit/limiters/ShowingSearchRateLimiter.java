package module.config.ratelimit.limiters;

import java.util.Collections;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import exception.BusinessError;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShowingSearchRateLimiter implements module.config.ratelimit.limiters.RateLimiter {

	private static final String LIMIT_PER_SECOND = "2";
	private static final String LIMIT_PER_MINUTE = "50";
	private static final DefaultRedisScript<Boolean> LUA_SCRIPT = new DefaultRedisScript(
		"""
			local prefix = KEYS[1]
			local ip = ARGV[1]
			local LIMIT_PER_SECOND = tonumber(ARGV[2])
			local LIMIT_PER_MINUTE = tonumber(ARGV[3])
			
			local blocked_ip_key = prefix .. ':BlockedIp:' .. ip
			local request_counter_key = prefix .. ':PerMinuteRequestCounter:' .. ip
			local per_second_counter_key = prefix .. ':PerSecondRequestCounter:' .. ip
			
			if redis.call('EXISTS', blocked_ip_key) == 1 then 
				return false 
			end
			
			local per_second_count = redis.call('INCR', per_second_counter_key)
			if per_second_count == 1 then 
				redis.call('EXPIRE', per_second_counter_key, 1) end
			if per_second_count > LIMIT_PER_SECOND then
				return false 
			end
			
			local current_count = redis.call('INCR', request_counter_key)
			if current_count == 1 then 
				redis.call('EXPIRE', request_counter_key, 60) 
			end
			if current_count > LIMIT_PER_MINUTE then
			   redis.call('SET', blocked_ip_key, 1)
			   redis.call('EXPIRE', blocked_ip_key, 3600)
			   return false
			end
			
			return true
		""", Boolean.class);

	private final StringRedisTemplate redisTemplate;
	private final String KEY_PREFIX = "hanghaeho:showingSearch";

	@Override
	public boolean preCheck(Object[] args, ContentCachingRequestWrapper request){
		String ip = request.getRemoteAddr();
		boolean isAllowed = Boolean.TRUE.equals(redisTemplate.execute(
			LUA_SCRIPT,
			Collections.singletonList(KEY_PREFIX),
			ip, LIMIT_PER_SECOND, LIMIT_PER_MINUTE
		));

		if(!isAllowed){
			throw BusinessError.SHOWING_TOO_MANY_REQUEST.exception();
		}

		return true;
	}
}
