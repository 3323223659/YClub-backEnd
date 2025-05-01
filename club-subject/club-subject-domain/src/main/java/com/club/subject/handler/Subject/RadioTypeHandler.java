package com.club.subject.handler.Subject;

import com.club.subject.basic.entity.SubjectRadio;
import com.club.subject.basic.service.SubjectRadioService;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.common.enums.SubjectInfoTypeEnum;
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
 * @Description: 单选题目策略类
 */

@Slf4j
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getSubjectInfoType() {
        return SubjectInfoTypeEnum.SUBJECT_TYPE_1;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题目新增
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        if(CollectionUtils.isEmpty(subjectInfoBO.getOptionList())){
            log.error("单选题目新增失败，题目id为：{}，题目名称为：{}，题目选项为空", subjectInfoBO.getId(), subjectInfoBO.getSubjectName());
            return;
        }
        subjectInfoBO.getOptionList().forEach(subjectAnswerBO -> {
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.convertBOToRadio(subjectAnswerBO);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });

        subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(Long subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        List <SubjectRadio> subjectRadioList = subjectRadioService.queryByCondition(subjectRadio);
        if(CollectionUtils.isEmpty(subjectRadioList)){
            log.error("单选题目详情查询失败，题目id为：{}，题目选项为空", subjectId);
            return null;
        }
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectRadioConverter.INSTANCE.convertRadioListToAnswerBOList(subjectRadioList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
