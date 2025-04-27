package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.entity.SubjectCategoryBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T01:32:14+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class SubjectCategoryConverterImpl implements SubjectCategoryConverter {

    @Override
    public SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategory subjectCategory = new SubjectCategory();

        subjectCategory.setId( subjectCategoryBO.getId() );
        subjectCategory.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategory.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategory.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategory.setParentId( subjectCategoryBO.getParentId() );

        return subjectCategory;
    }
}
