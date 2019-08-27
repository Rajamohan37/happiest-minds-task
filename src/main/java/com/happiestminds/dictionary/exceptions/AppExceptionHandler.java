package com.happiestminds.dictionary.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(WordNotFoundInDBException.class)
	public ResponseEntity<Object> handleWordNotFoundInDBException(WordNotFoundInDBException ex) {
		return new ResponseEntity<Object>(prepareError(ex, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileExtensionNotSupportedException.class)
	public ResponseEntity<Object> handleFileExtensionNotSupportedException(FileExtensionNotSupportedException ex) {
		return new ResponseEntity<Object>(prepareError(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	private Error prepareError(RuntimeException ex, HttpStatus statusCode) {
		Error error = new Error();
		error.setMessage(ex.getMessage());
		error.setStatusCode(statusCode.value());
		error.setStatus(statusCode);
		error.setTimeStamp(LocalDateTime.now());
		return error;
	}

}
