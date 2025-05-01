package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectInfoDTO;
import com.club.subject.entity.SubjectCategoryBO;
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
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDtoToInfoBo(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBoToInfoDto(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoDTO> convertBOToInfoDTOList(List<SubjectInfoBO> subjectInfoBOList);

}
