package com.example.luna.exceptions;

public class AesKeyException extends Exception {

	private static final long serialVersionUID = 8520055876085758267L;

	public AesKeyException() {
		super();
	}

	public AesKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public AesKeyException(String message) {
		super(message);
	}

	public AesKeyException(Throwable cause) {
		super(cause);
	}
}
