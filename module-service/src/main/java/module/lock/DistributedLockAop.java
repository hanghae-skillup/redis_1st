package module.lock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {

	private static final String REDISSON_LOCK_PREFIX = "LOCK:";
	private final RedissonClient redissonClient;

	@Around("@annotation(module.lock.DistributedLock)")
	public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

		String keyPrefix = distributedLock.keyPrefix();
		Object keys = CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(),
			distributedLock.key());

		// Lock 대상이 여러 개일 경우를 대비하여
		// Collection이나 Array로 변환하여 Lock 점유
		Object[] keysArr;
		if(keys instanceof Collection<?>){
			keysArr = ((Collection<?>)keys).toArray();
		} else if( keys.getClass().isArray() ){
			keysArr = (Object[]) keys;
		} else {
			keysArr = new Object[] {keys};
		}

		// key 배열만큼 락 생성
		List<RLock> lockList = Arrays.stream(keysArr)
			.map(key -> redissonClient.getLock(REDISSON_LOCK_PREFIX + keyPrefix + key))
			.toList();

		try{
			// 배열 내 모든 락 점유 시도
			// 하나라도 점유 실패 시 false 저장
			boolean allAvailable = lockList.parallelStream()
				.map(lock -> {
					try {
						return lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}).noneMatch(available->!available);

			if(!allAvailable){
				return false;
			}

			return joinPoint.proceed();
		} finally {

			// 메소드 종료 후 모든 락 점유 해제
			lockList.forEach(lock -> {
				try{
					lock.unlock();
				} catch (IllegalMonitorStateException e){
					log.info("Already unlocked lock : {}", lock.getName());
				}
			});
		}

	}
}
