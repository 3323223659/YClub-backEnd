package com.club.subject.handler.Subject;

import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.entity.SubjectInfoBO;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.entity.SubjectOptionBO;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/30/1:40
 * @Description: 题目新增处理接口
 */

@Component
public interface SubjectTypeHandler {

    /**
    获取题目的类型
    */
    SubjectInfoTypeEnum  getSubjectInfoType();

    /**
    实际题目的插入
    */
    void add(SubjectInfoBO subjectInfoBO);

    /**
    查询题目答案
    */
    SubjectOptionBO querySubjectInfo(Long subjectId);
}
