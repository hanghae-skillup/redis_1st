package exception.ticket;

public class InvalidTicketException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "부적절한 예매입니다. 재시도 바랍니다.";
	public InvalidTicketException() {
		super(DEFAULT_MESSAGE);
	}
}
