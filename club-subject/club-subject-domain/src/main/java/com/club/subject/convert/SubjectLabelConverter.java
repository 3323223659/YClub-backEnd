package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:40
 * @Description:
 */

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBOToLabel(SubjectLabelBO subjectLabelBO);

    SubjectLabelBO convertLabelToBO(SubjectLabel subjectLabel);

    List<SubjectLabelBO> convertLabelListToBOList(List<SubjectLabel> subjectLabelList);

}
