package com.opt.exception;

import java.sql.SQLException;

public class SQLOperationException extends SQLException {

	/**
	 * @author Santhanakumar.v
	 */
	private static final long serialVersionUID = 1L;

	public SQLOperationException(String message) {
		super(message);
	}

}
