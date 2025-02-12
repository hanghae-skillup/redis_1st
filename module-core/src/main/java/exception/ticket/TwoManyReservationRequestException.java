package exception.ticket;

public class TwoManyReservationRequestException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "5분 뒤 다시 시도해주세요";
	public TwoManyReservationRequestException() {
		super(DEFAULT_MESSAGE);
	}
}
