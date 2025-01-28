package com.opt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opt.dto.UserDTO;
import com.opt.entity.User;
import com.opt.entity.repository.UserRepository;
import com.opt.exception.UserAlreadyExistsException;
import com.opt.exception.UserNotFoundException;
import com.opt.service.UserService;
import com.opt.service.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	/**
	 * @author Santhanakumar.v
	 */

	private UserRepository userRepository;

	private UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	/**
	 * method to save the new user details
	 */
	@Override
	public UserDTO create(UserDTO userDTO) throws Exception {

		log.info("UserServiceImpl::create started");

		log.debug("UserServiceImpl::create params {}", userDTO);

		Optional<User> findUserByUsername = userRepository.findByUserName(userDTO.getUserName());

		if (findUserByUsername.isPresent()) {
			throw new UserAlreadyExistsException("Username already exists!");
		}

		Optional<User> findUserByEmail = userRepository.findByEmail(userDTO.getEmail());

		if (findUserByEmail.isPresent()) {
			throw new UserAlreadyExistsException("Email already exists!");
		}

		User user = userMapper.dtoToEntity(userDTO);

		log.debug("UserServiceImpl::create entity {}", user);

		User userRecord = userRepository.save(user);

		log.debug("UserServiceImpl::create saved successfully {}", user);

		UserDTO userRecordDTO = userMapper.entityToDTO(userRecord);

		log.info("UserServiceImpl::create completed");

		return userRecordDTO;

	}

	/**
	 * method to get all ther users
	 */
	@Override
	public List<UserDTO> fetchAll() {

		log.info("UserServiceImpl::fetchAll started");

		List<User> userRecords = userRepository.findAll();

		log.debug("UserServiceImpl::fetchAll fetched records {}", userRecords);

		List<UserDTO> userRecordDtos = userRecords.stream().map(user -> userMapper.entityToDTO(user)).toList();

		log.info("UserServiceImpl::fetchAll completed");

		return userRecordDtos;
	}

	/**
	 * fetch the user details by username
	 */
	@Override
	public UserDTO findByUserName(String userName) throws Exception {

		log.info("UserServiceImpl::findByUserName started");

		log.debug("UserServiceImpl::findByUserName params {}", userName);

		User record = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User Not found!"));

		log.debug("UserServiceImpl::findByUserName fetched record {}", record);

		UserDTO recordDTO = userMapper.entityToDTO(record);

		log.info("UserServiceImpl::findByUserName completed");

		return recordDTO;
	}

	/**
	 * to delete the user by username
	 */
	@Override
	public void deleteByUserName(String userName) throws Exception {

		log.info("UserServiceImpl::deleteByUserName started");

		log.debug("UserServiceImpl::deleteByUserName params {}", userName);

		User record = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User Not found!"));

		log.debug("UserServiceImpl::deleteByUserName fetched record {}", record);

		userRepository.delete(record);

		log.info("UserServiceImpl::deleteByUserName completed");
	}

}
