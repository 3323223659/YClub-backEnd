package com.club.subject.service;
import com.club.subject.common.entity.PageResult;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.entity.SubjectInfoBO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

public interface SubjectInfoDomainService {

    //新增题目
    void add(SubjectInfoBO subjectInfoBO);

    //分页查询题目列表
    PageResult<SubjectInfoBO> getPage(SubjectInfoBO subjectInfoBO);

    //查询题目详情
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
