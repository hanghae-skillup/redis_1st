package exception.ticket;

public class NoReservationInfoException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "예매정보가 없습니다.";
	public NoReservationInfoException() {
		super(DEFAULT_MESSAGE);
	}
}
