package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=ItemNotFoundException.class)
	protected ResponseEntity<String> handleConflict(
			RuntimeException ex, WebRequest request) {
  		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
