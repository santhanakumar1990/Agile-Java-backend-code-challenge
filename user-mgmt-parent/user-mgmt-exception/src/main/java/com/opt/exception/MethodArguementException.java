package com.opt.exception;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MethodArguementException extends Exception {

	private Map<String, String> fieldErrors;

	public MethodArguementException(String message) {
		super(message);
	}

	public MethodArguementException(String message, Map<String, String> fieldErrors) {
		super(message);
		this.fieldErrors = fieldErrors;
	}

}
