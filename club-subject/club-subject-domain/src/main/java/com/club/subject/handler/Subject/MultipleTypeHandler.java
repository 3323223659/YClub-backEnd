package com.club.subject.handler.Subject;

import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.basic.entity.SubjectRadio;
import com.club.subject.basic.service.SubjectMappingService;
import com.club.subject.basic.service.SubjectMultipleService;
import com.club.subject.basic.service.SubjectRadioService;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.convert.SubjectBriefConverter;
import com.club.subject.convert.SubjectMultipleConverter;
import com.club.subject.convert.SubjectRadioConverter;
import com.club.subject.entity.SubjectAnswerBO;
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
 * @Description: 多选题目策略类
 */

@Component
@Slf4j
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;
    @Override
    public SubjectInfoTypeEnum getSubjectInfoType() {
        return SubjectInfoTypeEnum.SUBJECT_TYPE_2;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //多选题目新增
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        if(CollectionUtils.isEmpty(subjectInfoBO.getOptionList())){
            log.error("多选题目新增失败，题目id为：{}，题目名称为：{}，题目选项为空", subjectInfoBO.getId(), subjectInfoBO.getSubjectName());
            return;
        }
        subjectInfoBO.getOptionList().forEach(subjectAnswerBO -> {
            SubjectMultiple subjectMultiple = SubjectMultipleConverter.INSTANCE.convertBOToMultiple(subjectAnswerBO);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });

        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.queryByCondition(subjectMultiple);
        if(CollectionUtils.isEmpty(subjectMultipleList)){
            log.error("多选题目详情查询失败，题目id为：{}，题目选项为空", subjectId);
            return null;
        }
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectMultipleConverter.INSTANCE.convertMultipleListToAnswerBOList(subjectMultipleList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
