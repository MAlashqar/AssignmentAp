package com.assignment.Assignment.dto;

import com.assignment.Assignment.beans.NewsDTO;
import com.assignment.Assignment.entity.NewsEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-02T16:38:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsEntityToDTO(NewsEntity newsEntity) {
        if ( newsEntity == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( newsEntity.getId() );
        newsDTO.setTitle( newsEntity.getTitle() );
        newsDTO.setTitleArabic( newsEntity.getTitleArabic() );
        newsDTO.setDescription( newsEntity.getDescription() );
        newsDTO.setDescriptionArabic( newsEntity.getDescriptionArabic() );
        newsDTO.setPublishDate( newsEntity.getPublishDate() );
        newsDTO.setImageUrl( newsEntity.getImageUrl() );
        newsDTO.setDeleted( newsEntity.getDeleted() );
        newsDTO.setDeleteRequest( newsEntity.getDeleteRequest() );

        return newsDTO;
    }

    @Override
    public NewsEntity newsDTOToEntity(NewsDTO newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        NewsEntity newsEntity = new NewsEntity();

        newsEntity.setId( newsDTO.getId() );
        newsEntity.setTitle( newsDTO.getTitle() );
        newsEntity.setTitleArabic( newsDTO.getTitleArabic() );
        newsEntity.setDescription( newsDTO.getDescription() );
        newsEntity.setDescriptionArabic( newsDTO.getDescriptionArabic() );
        newsEntity.setPublishDate( newsDTO.getPublishDate() );
        newsEntity.setImageUrl( newsDTO.getImageUrl() );
        newsEntity.setStatus( newsDTO.getStatus() );
        newsEntity.setDeleted( newsDTO.getDeleted() );
        newsEntity.setDeleteRequest( newsDTO.getDeleteRequest() );

        return newsEntity;
    }
}
