package com.club.subject.basic.service;

import com.club.subject.basic.entity.SubjectBrief;

import java.util.List;

/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author makejava
 * @since 2025-04-30 00:52:37
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量修改数据
     */
    int batchInsert(List<SubjectBrief> subjectBriefList);

    /**
     * 通过实体作为筛选条件查询
     */
    SubjectBrief queryByCondition(SubjectBrief subjectBrief);
}
