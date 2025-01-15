package module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(HandlerMethodValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<ErrorResponse> notValidParameter(HandlerMethodValidationException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.builder()
				.errorCode(400)
				.message(e.getParameterValidationResults().get(0).getResolvableErrors().get(0).getDefaultMessage())
				.build());
	}
}
