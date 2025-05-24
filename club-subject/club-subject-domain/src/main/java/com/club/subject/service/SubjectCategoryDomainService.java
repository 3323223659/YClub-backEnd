package com.club.subject.service;
import com.club.subject.entity.SubjectCategoryBO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    // 查询岗位分类
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    //更新分类
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    //删除分类
    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    //一次性查询分类及标签
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
