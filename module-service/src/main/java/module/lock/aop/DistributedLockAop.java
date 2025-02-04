package module.lock.aop;

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

import exception.common.TryLockFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {

	private static final String REDISSON_LOCK_PREFIX = "LOCK:";
	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;

	@Around("@annotation(module.lock.aop.DistributedLock)")
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
			.sorted() // 교착상태 방지 ex: A : 1,2 / B : 2,1
			.map(key -> redissonClient.getLock(REDISSON_LOCK_PREFIX + keyPrefix + key))
			.toList();

		try{
			// 락 점유 하나라도 점유 실패 시 TryLockFailedException
			boolean allAvailable = true;
			for (RLock lock : lockList) {
				try{
					if(!lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(),distributedLock.timeUnit())){
						allAvailable = false;
						break;
					}
				} catch (InterruptedException e){
					allAvailable = false;
					break;
				}
			}

			// 배열내 모든 락이 점유 실패
			if(!allAvailable){
				// 순차적으로 락을 해제하도록 변경
				for (RLock lock : lockList) {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}
				throw new TryLockFailedException();
			}

			return aopForTransaction.proceed(joinPoint);
		} finally {
			// 메소드 종료 후 모든 락 점유 해제
			lockList.forEach(lock -> {
				if (lock.isHeldByCurrentThread()) {
					try {
						lock.unlock();
					} catch (IllegalMonitorStateException e) {
						log.warn("Lock was already released: {}", lock.getName());
					}
				}
			});
		}

	}
}
