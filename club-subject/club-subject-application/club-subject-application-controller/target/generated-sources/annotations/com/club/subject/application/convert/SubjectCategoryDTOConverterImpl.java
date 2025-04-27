package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.entity.SubjectCategoryBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T01:32:22+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertDtoToCategoryBo(SubjectCategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( categoryDTO.getId() );
        subjectCategoryBO.setCategoryName( categoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( categoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( categoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( categoryDTO.getParentId() );

        return subjectCategoryBO;
    }
}
