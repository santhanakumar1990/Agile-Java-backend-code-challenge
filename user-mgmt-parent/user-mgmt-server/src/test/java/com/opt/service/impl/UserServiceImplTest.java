package com.opt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.opt.dto.UserDTO;
import com.opt.entity.User;
import com.opt.entity.repository.UserRepository;
import com.opt.service.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createNewUserTest() throws Exception {

		User user = new User();
		user.setUserName("xyz");
		user.setEmail("xyz@gmail.com");

		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("xyz");
		userDTO.setEmail("xyz@gmail.com");

		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(Optional.empty());

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		Mockito.when(userMapper.dtoToEntity(Mockito.any(UserDTO.class))).thenReturn(user);

		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		Mockito.when(userMapper.entityToDTO(Mockito.any(User.class))).thenReturn(userDTO);

		UserDTO resultDTO = userServiceImpl.create(userDTO);

		assertEquals(userDTO.getUserName(), resultDTO.getUserName());

	}

	@Test
	public void fetAllUserTest() throws Exception {

		User user = new User();
		user.setUserName("xyz");
		user.setEmail("xyz@gmail.com");

		List<User> users = List.of(user);

		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("xyz");
		userDTO.setEmail("xyz@gmail.com");

		Mockito.when(userRepository.findAll()).thenReturn(users);

		Mockito.when(userMapper.entityToDTO(user)).thenReturn(userDTO);

		List<UserDTO> resultDTO = userServiceImpl.fetchAll();

		assertEquals(userDTO, resultDTO.get(0));

	}

	@Test
	public void findByUserNameTest() throws Exception {

		User user = new User();
		user.setUserName("xyz");
		user.setEmail("xyz@gmail.com");

		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("xyz");
		userDTO.setEmail("xyz@gmail.com");

		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(Optional.of(user));

		Mockito.when(userMapper.entityToDTO(Mockito.any(User.class))).thenReturn(userDTO);

		UserDTO resultDTO = userServiceImpl.findByUserName("xyz");

		assertEquals(userDTO, resultDTO);

	}

	@Test
	public void deleteByUserNameTest() throws Exception {

		User user = new User();
		user.setUserName("xyz");
		user.setEmail("xyz@gmail.com");

		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(Optional.of(user));

		Mockito.doNothing().when(userRepository).delete(Mockito.any(User.class));
		
		userServiceImpl.deleteByUserName("xyz");

	}

}
