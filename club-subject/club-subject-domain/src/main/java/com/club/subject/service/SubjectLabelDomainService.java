package com.club.subject.service;

import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.entity.SubjectLabelBO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/20:37
 * @Description:
 */

public interface SubjectLabelDomainService {

    //新增标签
    void add(SubjectLabelBO subjectLabelBO);

    //修改标签
    Boolean update(SubjectLabelBO subjectLabelBO);

    //删除标签
    Boolean delete(SubjectLabelBO subjectLabelBO);

    //根据分类id查询标签
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
