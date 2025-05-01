package com.club.subject.handler.Subject;

import com.club.subject.basic.entity.SubjectBrief;
import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.basic.service.SubjectBriefService;
import com.club.subject.basic.service.SubjectMultipleService;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.convert.SubjectBriefConverter;
import com.club.subject.convert.SubjectMultipleConverter;
import com.club.subject.entity.SubjectInfoBO;
import com.club.subject.entity.SubjectOptionBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/30/1:42
 * @Description: 简答题目策略类
 */

@Component
@Slf4j
public class BriefTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectBriefService subjectBriefService;
    @Override
    public SubjectInfoTypeEnum getSubjectInfoType() {
        return SubjectInfoTypeEnum.SUBJECT_TYPE_4;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.convertInfoBoToBrief(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        if (result != null){
            subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        }
        return subjectOptionBO;
    }

}
