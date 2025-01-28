package com.opt.service.mapper;

import java.util.Optional;

import org.aspectj.lang.annotation.AfterReturning;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.opt.dto.UserDTO;
import com.opt.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	/**
	 * @author Santhanakumar.v
	 */

	public User dtoToEntity(UserDTO userDTO);

	@Mappings(@Mapping(source = "id", target = "userId"))
	public UserDTO entityToDTO(User user);

}
