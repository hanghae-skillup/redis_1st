package module.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

	/**
	 * 락 키의 접두어
	 */
	String keyPrefix();

	/**
	 * 락 키값 [ 배열 설정 가능 ]
	 */
	String key();

	/**
	 * 락의 시간 단위
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * 락을 기다리는 시간 (default - 5s)
	 */
	long waitTime() default 5L;

	/**
	 * 락 임대 시간 (default - 3s)
	 */
	long leaseTime() default 3L;

}
