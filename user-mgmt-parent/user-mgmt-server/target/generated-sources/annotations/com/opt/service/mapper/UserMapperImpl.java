package com.opt.service.mapper;

import com.opt.dto.UserDTO;
import com.opt.dto.UserDTO.UserDTOBuilder;
import com.opt.entity.User;
import com.opt.entity.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-29T01:24:24+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.userName( userDTO.getUserName() );
        user.name( userDTO.getName() );
        user.email( userDTO.getEmail() );
        user.gender( userDTO.getGender() );
        user.picture( userDTO.getPicture() );

        return user.build();
    }

    @Override
    public UserDTO entityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        if ( user.getId() != null ) {
            userDTO.userId( String.valueOf( user.getId() ) );
        }
        userDTO.userName( user.getUserName() );
        userDTO.name( user.getName() );
        userDTO.email( user.getEmail() );
        userDTO.gender( user.getGender() );
        userDTO.picture( user.getPicture() );

        return userDTO.build();
    }
}
