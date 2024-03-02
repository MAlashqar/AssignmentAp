package com.assignment.Assignment.dto;


import com.assignment.Assignment.beans.NewsDTO;
import com.assignment.Assignment.entity.NewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    @Mapping(target = "status", ignore = true)
    NewsDTO newsEntityToDTO(NewsEntity newsEntity);

    NewsEntity newsDTOToEntity(NewsDTO newsDTO);
}
