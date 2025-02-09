package exception.ticket;

public class InvalidAgeForMovieException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "상영영화의 관람등급과 고객님의 나이가 맞지 않습니다.";
	public InvalidAgeForMovieException() {
		super(DEFAULT_MESSAGE);
	}
}
