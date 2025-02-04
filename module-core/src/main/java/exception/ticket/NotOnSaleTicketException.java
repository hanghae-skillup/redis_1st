package exception.ticket;

public class NotOnSaleTicketException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "이미 예매중인 티켓입니다.";
	public NotOnSaleTicketException() {
		super(DEFAULT_MESSAGE);
	}
}
