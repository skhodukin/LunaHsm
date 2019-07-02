package com.example.luna.exceptions;

public class AesException extends Exception {

	private static final long serialVersionUID = 6707813504864570525L;

	public AesException() {
		super();
	}

	public AesException(String message, Throwable cause) {
		super(message, cause);
	}

	public AesException(String message) {
		super(message);
	}

	public AesException(Throwable cause) {
		super(cause);
	}
}
