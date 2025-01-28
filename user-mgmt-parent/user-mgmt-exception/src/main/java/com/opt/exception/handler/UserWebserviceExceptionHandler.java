package com.opt.exception.handler;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.opt.common.ResponseDTO;
import com.opt.exception.EmailAlreadyExistsException;
import com.opt.exception.MethodArguementException;
import com.opt.exception.UserAlreadyExistsException;
import com.opt.exception.UserNotFoundException;

@ControllerAdvice
public class UserWebserviceExceptionHandler {

	/**
	 * Exception handler for handling dupliate email and duplicate useraccount
	 * exception
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ EmailAlreadyExistsException.class, UserAlreadyExistsException.class })
	public ResponseEntity<ResponseDTO<String>> handle403Exception(Exception exception) {

		ResponseDTO<String> result = ResponseDTO.<String>builder().message(exception.getMessage()).status(false)
				.build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
	}

	/**
	 * Exception handler for usernot found exception
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<ResponseDTO<String>> handle404Exception(Exception exception) {

		ResponseDTO<String> result = ResponseDTO.<String>builder().message(exception.getMessage()).status(false)
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}

	/**
	 * Handle validation errors;
	 * 
	 * @param exception
	 * @return
	 */

	@ExceptionHandler({ MethodArguementException.class })
	public ResponseEntity<ResponseDTO<Map<String, String>>> handleValidationErrors(Exception exception) {

		ResponseDTO<Map<String, String>> result = ResponseDTO.<Map<String, String>>builder()
				.message(exception.getMessage()).status(false).build();

		if (exception instanceof MethodArguementException) {

			MethodArguementException methodArguementException = (MethodArguementException) exception;

			result = ResponseDTO.<Map<String, String>>builder().message(exception.getMessage())
					.errors(methodArguementException.getFieldErrors()).status(false).build();
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	/**
	 * Exception handler for handling generic excepption
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ SQLException.class, Exception.class })
	public ResponseEntity<ResponseDTO<String>> handle500Exception(Exception exception) {

		ResponseDTO<String> result = ResponseDTO.<String>builder()
				.message("Internal server error!, please contact Administrator").status(false).build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}

}
