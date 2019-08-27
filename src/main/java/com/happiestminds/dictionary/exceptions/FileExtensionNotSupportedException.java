package com.happiestminds.dictionary.exceptions;

public class FileExtensionNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5799218923828311904L;
	
	private String message;

	public FileExtensionNotSupportedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
