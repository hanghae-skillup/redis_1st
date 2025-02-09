package exception.ticket;

public class TooManyReservationException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "한 시간표에 1인 5매의 티켓만 구매 가능합니다.";
	public TooManyReservationException() {
		super(DEFAULT_MESSAGE);
	}
}
