package com.rsystems.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UrlFoundException.class)
	public ResponseEntity<StandardError> urlFound(UrlFoundException e, HttpServletRequest httpServletRequest) {
		StandardError error = new StandardError(HttpStatus.ACCEPTED.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(error);
	}
	
	@ExceptionHandler(UrlNotFoundException.class)
	public ResponseEntity<StandardError> urlNotFound(UrlNotFoundException e, HttpServletRequest httpServletRequest) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<StandardError> unAuthorizedException(UnAuthorizedException e, HttpServletRequest httpServletRequest) {
		StandardError error = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

}
