package com.journey.vanita.customexceptions;

public class BookingsFullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6226135127199456561L;

	public BookingsFullException() {
	}

	public BookingsFullException(String message) {
		super(message);
	}

	public BookingsFullException(Throwable cause) {
		super(cause);
	}

	public BookingsFullException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingsFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
