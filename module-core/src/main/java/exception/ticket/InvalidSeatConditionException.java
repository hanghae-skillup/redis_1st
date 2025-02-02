package exception.ticket;

public class InvalidSeatConditionException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "여러 티켓 구매의 경우 연속된 좌석만 구매하실 수 있습니다.";
	public InvalidSeatConditionException() {
		super(DEFAULT_MESSAGE);
	}
}
