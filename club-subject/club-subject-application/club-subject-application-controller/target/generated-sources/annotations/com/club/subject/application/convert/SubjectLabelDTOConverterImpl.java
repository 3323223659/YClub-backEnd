package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectLabelDTO;
import com.club.subject.entity.SubjectLabelBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-09T14:45:11+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_451 (Oracle Corporation)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBO convertDtoToLabelBo(SubjectLabelDTO labelDTO) {
        if ( labelDTO == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( labelDTO.getId() );
        subjectLabelBO.setLabelName( labelDTO.getLabelName() );
        subjectLabelBO.setSortNum( labelDTO.getSortNum() );
        subjectLabelBO.setCategoryId( labelDTO.getCategoryId() );

        return subjectLabelBO;
    }

    @Override
    public List<SubjectLabelDTO> convertBOToLabelBoList(List<SubjectLabelBO> subjectLabelBOList) {
        if ( subjectLabelBOList == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( subjectLabelBOList.size() );
        for ( SubjectLabelBO subjectLabelBO : subjectLabelBOList ) {
            list.add( subjectLabelBOToSubjectLabelDTO( subjectLabelBO ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBOToSubjectLabelDTO(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBO.getId() );
        subjectLabelDTO.setCategoryId( subjectLabelBO.getCategoryId() );
        subjectLabelDTO.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBO.getSortNum() );

        return subjectLabelDTO;
    }
}
