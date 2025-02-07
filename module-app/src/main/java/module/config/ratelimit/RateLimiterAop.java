package module.config.ratelimit;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.RequiredArgsConstructor;
import module.config.ratelimit.limiters.RateLimiter;
import module.config.ratelimit.limiters.RateLimiterFactory;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAop {

	private final RateLimiterFactory rateLimiterFactory;

	@Around("@annotation(module.config.ratelimit.RateLimitWith)")
	public Object rateLimiter(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();

		// 설정된 RateLimiter 조회
		RateLimitWith rateLimitWith = method.getAnnotation(RateLimitWith.class);

		// HttpServletRequest 조회 및 캐싱
		ServletRequestAttributes attributes =
			(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(attributes.getRequest());

		RateLimiter rateLimiter = rateLimiterFactory.getRateLimiter(rateLimitWith.rateLimiter());

		if(rateLimitWith.preProcess()){
			if(!rateLimiter.preCheck(joinPoint.getArgs(), wrappedRequest)){
				return false;
			}
		}

		Object result = joinPoint.proceed();

		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(attributes.getResponse());
		if(rateLimitWith.postProcess()){
			rateLimiter.postCheck(joinPoint.getArgs(), wrappedResponse);
		}

		return result;
	}
}
