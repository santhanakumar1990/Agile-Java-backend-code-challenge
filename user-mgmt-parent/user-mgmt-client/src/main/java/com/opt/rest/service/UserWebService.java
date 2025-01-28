package com.opt.rest.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opt.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")

@Tag(name = "User Management Service")
public interface UserWebService {

	/**
	 * Api Operation for create new user
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "Create new User")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User Creation Success"),
			@ApiResponse(responseCode = "409", description = "User already exists, Email already Exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO, BindingResult result) throws Exception;

	/**
	 * Api operation for fetch all the users
	 * 
	 * @return
	 */
	@Operation(summary = "Fetch all the users")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User List fetched Success"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> fetchAll();

	/**
	 * api operation for fetch user by username
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "Fetch User by username")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User Details fetched Success"),
			@ApiResponse(responseCode = "404", description = "User not available"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping(path = "/users/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findByUserName(@PathVariable String userName) throws Exception;

	@Operation(summary = "Delete the user by username")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User deleted Success"),
			@ApiResponse(responseCode = "404", description = "User not available"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping(path = "/users/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteByUserName(@PathVariable String userName) throws Exception;

}
