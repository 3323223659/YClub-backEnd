package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectJudge;
import com.club.subject.basic.entity.SubjectMultiple;
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
public interface SubjectJudgeConverter {

    SubjectJudgeConverter INSTANCE = Mappers.getMapper(SubjectJudgeConverter.class);

    SubjectJudge convertBOToJudge(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertJudgeListToAnswerBOList(List<SubjectJudge> subjectJudgeList);
}
