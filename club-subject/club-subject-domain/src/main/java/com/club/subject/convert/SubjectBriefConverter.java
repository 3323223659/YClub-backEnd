package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectBrief;
import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.entity.SubjectAnswerBO;
import com.club.subject.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:40
 * @Description:
 */

@Mapper
public interface SubjectBriefConverter {

    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);

    SubjectBrief convertInfoBoToBrief(SubjectInfoBO subjectInfoBO);

}
