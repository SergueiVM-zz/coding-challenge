package com.liberty.interview.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.liberty.interview.error.PokerPlanningError;
import com.liberty.interview.exception.MismatchException;
import com.liberty.interview.exception.NotFoundException;

@ControllerAdvice
public class PokerPlanningControllerAdvice {

	@ExceptionHandler(value = { NotFoundException.class, MismatchException.class })
	protected ResponseEntity<PokerPlanningError> notFoundResourceHandler(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new PokerPlanningError(buildErrorCode(HttpStatus.NOT_FOUND), ex.getMessage()));
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<PokerPlanningError> unprocessableEntityHandler(HttpMessageNotReadableException ex) {

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new PokerPlanningError(buildErrorCode(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage()));
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<List<PokerPlanningError>> notValidArgumentsHandler(MethodArgumentNotValidException ex) {

		List<PokerPlanningError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(validationError -> new PokerPlanningError(buildErrorCode(HttpStatus.BAD_REQUEST),
						buildErrorMessage(validationError.getField(), validationError.getDefaultMessage())))
				.distinct().collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}

	private String buildErrorCode(HttpStatus httpStatus) {
		return String.format("%s (%s)", httpStatus.value(), httpStatus.getReasonPhrase());
	}

	private String buildErrorMessage(String field, String validationError) {
		return String.format("Validation Error -> %s -> %s", field, validationError);
	}

}
