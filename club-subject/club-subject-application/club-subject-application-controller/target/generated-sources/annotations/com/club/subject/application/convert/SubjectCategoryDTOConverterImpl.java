package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T19:23:14+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_451 (Oracle Corporation)"
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
        subjectCategoryBO.setCount( categoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertBOToCategoryDTOList(List<SubjectCategoryBO> categoryBOList) {
        if ( categoryBOList == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( categoryBOList.size() );
        for ( SubjectCategoryBO subjectCategoryBO : categoryBOList ) {
            list.add( convertBoToCategoryDTO( subjectCategoryBO ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryDTO convertBoToCategoryDTO(SubjectCategoryBO categoryBO) {
        if ( categoryBO == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( categoryBO.getId() );
        subjectCategoryDTO.setCategoryName( categoryBO.getCategoryName() );
        subjectCategoryDTO.setCategoryType( categoryBO.getCategoryType() );
        subjectCategoryDTO.setImageUrl( categoryBO.getImageUrl() );
        subjectCategoryDTO.setParentId( categoryBO.getParentId() );
        subjectCategoryDTO.setCount( categoryBO.getCount() );

        return subjectCategoryDTO;
    }
}
