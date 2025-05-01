package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectInfo;
import com.club.subject.basic.entity.SubjectRadio;
import com.club.subject.entity.SubjectAnswerBO;
import com.club.subject.entity.SubjectInfoBO;
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
public interface SubjectRadioConverter {

    SubjectRadioConverter INSTANCE = Mappers.getMapper(SubjectRadioConverter.class);

    SubjectRadio convertBOToRadio(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertRadioListToAnswerBOList(List<SubjectRadio> subjectRadioList);
}
