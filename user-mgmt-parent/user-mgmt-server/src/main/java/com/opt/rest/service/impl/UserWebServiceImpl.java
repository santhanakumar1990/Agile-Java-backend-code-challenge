package com.opt.rest.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.opt.dto.UserDTO;
import com.opt.exception.MethodArguementException;
import com.opt.rest.service.UserWebService;
import com.opt.service.UserService;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserWebServiceImpl implements UserWebService {

	/**
	 * @author Santhanakumar.v
	 */

	private UserService userService;

	public UserWebServiceImpl(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Reset api method to create new user
	 */

	@Override
	public ResponseEntity<UserDTO> create(UserDTO userDTO, BindingResult bindingResult) throws Exception {

		log.info("UserWebServiceImpl::create started");

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
					.collect(Collectors.toMap(field -> field.getField(), field -> field.getDefaultMessage()));
			throw new MethodArguementException("Input validation failed, please correct it", errorMap);
		}

		log.debug("UserWebServiceImpl::create params {}", userDTO);

		UserDTO result = this.userService.create(userDTO);

		log.info("UserWebServiceImpl::create completed");

		return ResponseEntity.status(HttpStatus.CREATED).body(result);

	}

	/**
	 * rest api method to get all the users
	 */
	@Override
	public ResponseEntity<List<UserDTO>> fetchAll() {

		log.info("UserWebServiceImpl::fetchAll started");

		List<UserDTO> result = this.userService.fetchAll();

		log.info("UserWebServiceImpl::fetchAll completed");

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * rest api method to get user by username
	 */
	@Override
	public ResponseEntity<UserDTO> findByUserName(String userName) throws Exception {

		log.info("UserWebServiceImpl::findByUserName started");

		log.debug("UserWebServiceImpl::findByUserName params {}", userName);

		UserDTO result = this.userService.findByUserName(userName);

		log.info("UserWebServiceImpl::findByUserName completed");

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * rest operation for delete the user details
	 */

	@Override
	public ResponseEntity<Void> deleteByUserName(String userName) throws Exception {

		log.info("UserWebServiceImpl::deleteByUserName started");

		log.debug("UserWebServiceImpl::deleteByUserName params {}", userName);

		this.userService.deleteByUserName(userName);

		log.info("UserWebServiceImpl::deleteByUserName completed");

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
