package module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

	// 비즈니스 규칙 Exception Handler
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<String> businessException(BusinessException businessException) {
		return ResponseEntity.status(businessException.getHttpStatus())
			.body(businessException.getMessage());
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	protected ResponseEntity<String> notValidParameter(HandlerMethodValidationException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getParameterValidationResults().get(0).getResolvableErrors().get(0).getDefaultMessage());
	}

}
