package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectLabelDTO;
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
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToLabelBo(SubjectLabelDTO labelDTO);

    List<SubjectLabelDTO> convertBOToLabelBoList(List<SubjectLabelBO> subjectLabelBOList);

    List<SubjectLabelDTO> convertBOToLabelDTOList(List<SubjectLabelBO> labelBOList);
}
