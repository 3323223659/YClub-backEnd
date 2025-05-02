package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.entity.SubjectLabelBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T22:26:08+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class SubjectLabelConverterImpl implements SubjectLabelConverter {

    @Override
    public SubjectLabel convertBOToLabel(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBO.getId() );
        subjectLabel.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabel.setSortNum( subjectLabelBO.getSortNum() );
        if ( subjectLabelBO.getCategoryId() != null ) {
            subjectLabel.setCategoryId( String.valueOf( subjectLabelBO.getCategoryId() ) );
        }

        return subjectLabel;
    }

    @Override
    public SubjectLabelBO convertLabelToBO(SubjectLabel subjectLabel) {
        if ( subjectLabel == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabel.getId() );
        subjectLabelBO.setLabelName( subjectLabel.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabel.getSortNum() );
        if ( subjectLabel.getCategoryId() != null ) {
            subjectLabelBO.setCategoryId( Long.parseLong( subjectLabel.getCategoryId() ) );
        }

        return subjectLabelBO;
    }
}
