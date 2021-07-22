package com.magicbricks.batch.exception;

public class MbRetryableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MbRetryableException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
