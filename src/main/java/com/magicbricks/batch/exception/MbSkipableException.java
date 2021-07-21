package com.magicbricks.batch.exception;

public class MbSkipableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MbSkipableException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
