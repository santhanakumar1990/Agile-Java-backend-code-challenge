package com.opt.exception;

public class EmailAlreadyExistsException extends Exception {

	/**
	 * @author Santhanakumar.v
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
