package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectBrief;
import com.club.subject.entity.SubjectInfoBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T04:43:58+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_451 (Oracle Corporation)"
)
public class SubjectBriefConverterImpl implements SubjectBriefConverter {

    @Override
    public SubjectBrief convertInfoBoToBrief(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );

        return subjectBrief;
    }
}
