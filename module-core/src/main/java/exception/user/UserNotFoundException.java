package exception.user;

public class UserNotFoundException extends RuntimeException{
	private final static String DEFAULT_MESSAGE = "존재하지 않는 id 잆니다.";
	public UserNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
}
