package hellojpa.exception;

public class SeatReservationException extends RuntimeException{

    // 기본 생성자
    public SeatReservationException() {
        super();
    }

    // 메시지를 받는 생성자
    public SeatReservationException(String message) {
        super(message);
    }

    // 메시지와 원인을 받는 생성자
    public SeatReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    // 원인을 받는 생성자
    public SeatReservationException(Throwable cause) {
        super(cause);
    }
}