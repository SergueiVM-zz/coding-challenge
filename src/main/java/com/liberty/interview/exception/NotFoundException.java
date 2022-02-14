package com.liberty.interview.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4974344938689440566L;

	public NotFoundException(String msg) {
		super(msg);
	}

}
