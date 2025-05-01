package com.club.subject.handler.Subject;

import com.club.subject.basic.entity.SubjectJudge;
import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.basic.service.SubjectJudgeService;
import com.club.subject.basic.service.SubjectMultipleService;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.convert.SubjectJudgeConverter;
import com.club.subject.convert.SubjectMultipleConverter;
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
 * @Description: 判断题目策略类
 */

@Component
@Slf4j
public class JudgeTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectInfoTypeEnum getSubjectInfoType() {
        return SubjectInfoTypeEnum.SUBJECT_TYPE_3;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断题目新增
        SubjectJudge subjectJudge = new SubjectJudge();
        if(CollectionUtils.isEmpty(subjectInfoBO.getOptionList())){
            log.error("判断题目新增失败，题目id为：{}，题目名称为：{}，题目选项为空", subjectInfoBO.getId(), subjectInfoBO.getSubjectName());
            return;
        }
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        List<SubjectJudge> subjectJudgeList = subjectJudgeService.queryByCondition(subjectJudge);
        if(CollectionUtils.isEmpty(subjectJudgeList)){
            log.error("判断题目详情查询失败，题目id为：{}，题目选项为空", subjectId);
            return null;
        }
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectJudgeConverter.INSTANCE.convertJudgeListToAnswerBOList(subjectJudgeList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
