package module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import exception.common.TryLockFailedException;
import exception.showing.ShowingNotFoundException;
import exception.ticket.InvalidAgeForMovieException;
import exception.ticket.InvalidSeatConditionException;
import exception.ticket.InvalidTicketException;
import exception.ticket.NotOnSaleTicketException;
import exception.ticket.TooManyReservationException;
import exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(HandlerMethodValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> notValidParameter(HandlerMethodValidationException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getParameterValidationResults().get(0).getResolvableErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> userNotFound(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(InvalidAgeForMovieException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> invalidReservation(InvalidAgeForMovieException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(InvalidSeatConditionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> invalidReservation(InvalidSeatConditionException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(InvalidTicketException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> invalidReservation(InvalidTicketException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(TooManyReservationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> invalidReservation(TooManyReservationException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(NotOnSaleTicketException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<String> invalidReservation(NotOnSaleTicketException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}

	@ExceptionHandler(ShowingNotFoundException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	protected ResponseEntity<String> showingNotFoundException(ShowingNotFoundException e){
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			.body(e.getMessage());
	}

	@ExceptionHandler(TryLockFailedException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	protected ResponseEntity<String> tryLockFailedException(TryLockFailedException e){
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			.body(e.getMessage());
	}
}
