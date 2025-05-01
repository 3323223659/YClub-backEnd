package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectAnswerDTO;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.entity.SubjectAnswerBO;
import com.club.subject.entity.SubjectCategoryBO;
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
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDtoToAnswerBo(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertListDtoToAnswerBo(List<SubjectAnswerDTO> subjectAnswerDTOList);

    List<SubjectAnswerDTO> convertBOToAnswerDTOList(List<SubjectAnswerBO> subjectAnswerBOList);

}
