package com.opt.service;

import java.util.List;

import com.opt.dto.UserDTO;

/**
 * @author santhanakumar.v
 */
public interface UserService {

	public UserDTO create(UserDTO userDTO) throws Exception;

	public List<UserDTO> fetchAll();

	public UserDTO findByUserName(String userName) throws Exception;
	
	public void deleteByUserName(String userName) throws Exception;

}