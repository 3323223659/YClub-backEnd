package com.club.subject.basic.service;

import com.club.subject.basic.entity.SubjectJudge;

import java.util.List;

/**
 * 判断题(SubjectJudge)表服务接口
 *
 * @author makejava
 * @since 2025-04-30 00:53:03
 */
public interface SubjectJudgeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectJudge queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge insert(SubjectJudge subjectJudge);

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge update(SubjectJudge subjectJudge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量新增数据
     */
    int batchInsert(List<SubjectJudge> subjectJudgeList);

    /**
     * 通过实体作为筛选条件查询
     */
    List<SubjectJudge> queryByCondition(SubjectJudge subjectJudge);
}
