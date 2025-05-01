package com.club.subject.convert;

import com.club.subject.basic.entity.SubjectInfo;
import com.club.subject.entity.SubjectInfoBO;
import com.club.subject.entity.SubjectOptionBO;
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
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertInfoToBO(SubjectInfo subjectInfo);

    SubjectInfoBO convertOptionAndInfoToBO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertInfoListToBOList(List<SubjectInfo> subjectInfoList);

}
