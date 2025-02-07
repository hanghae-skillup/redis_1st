package exception.showing;

public class TooManyRequestException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "요청을 너무 많이 보냈습니다.";
	public TooManyRequestException() {
		super(DEFAULT_MESSAGE);
	}

}
