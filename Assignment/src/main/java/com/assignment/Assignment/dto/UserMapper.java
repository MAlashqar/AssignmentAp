package com.assignment.Assignment.dto;

import com.assignment.Assignment.beans.UserDTO;
import com.assignment.Assignment.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true) // Ignore password field if needed
    UserDTO userEntityToDTO(UserEntity userEntity);

    UserEntity userDTOToEntity(UserDTO userDTO);
}
