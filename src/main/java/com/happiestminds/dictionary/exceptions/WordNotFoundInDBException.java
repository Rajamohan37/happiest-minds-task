package com.happiestminds.dictionary.exceptions;

public class WordNotFoundInDBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498838325518111247L;

	private String message;

	public WordNotFoundInDBException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
