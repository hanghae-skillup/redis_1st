package exception.showing;

public class ShowingNotFoundException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "존재하지 않는 상영정보입니다.";
	public ShowingNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
}
