package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.basic.entity.SubjectRadio;
import com.club.subject.entity.SubjectAnswerBO;
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
public interface SubjectMultipleConverter {

    SubjectMultipleConverter INSTANCE = Mappers.getMapper(SubjectMultipleConverter.class);

    SubjectMultiple convertBOToMultiple(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertMultipleListToAnswerBOList(List<SubjectMultiple> subjectMultipleList);

}
