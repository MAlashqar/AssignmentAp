package com.assignment.Assignment.dto;

import com.assignment.Assignment.beans.UserDTO;
import com.assignment.Assignment.beans.UserDTO.UserDTOBuilder;
import com.assignment.Assignment.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-02T16:38:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userEntityToDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( userEntity.getId() );
        userDTO.fullName( userEntity.getFullName() );
        userDTO.email( userEntity.getEmail() );
        userDTO.dateOfBirth( userEntity.getDateOfBirth() );
        userDTO.role( userEntity.getRole() );

        return userDTO.build();
    }

    @Override
    public UserEntity userDTOToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDTO.getId() );
        userEntity.setFullName( userDTO.getFullName() );
        userEntity.setEmail( userDTO.getEmail() );
        userEntity.setPassword( userDTO.getPassword() );
        userEntity.setDateOfBirth( userDTO.getDateOfBirth() );
        userEntity.setRole( userDTO.getRole() );

        return userEntity;
    }
}
