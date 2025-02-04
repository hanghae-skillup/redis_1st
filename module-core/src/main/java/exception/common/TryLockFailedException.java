package exception.common;

public class TryLockFailedException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "현재 접속자가 많습니다. 잠시후 재시도 바랍니다.";
	public TryLockFailedException() {
		super(DEFAULT_MESSAGE);
	}
}
