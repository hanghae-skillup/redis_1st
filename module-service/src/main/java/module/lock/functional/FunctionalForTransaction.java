package module.lock.functional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 함수형 분산락에서 트랜잭션 분리를 위한 클래스
 */
@Component
public class FunctionalForTransaction {
	private FunctionalForTransaction() {}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void run(Runnable runnable){
		runnable.run();
	}
}
