package com.club.subject.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.club.subject.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2025-04-30 00:53:24
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

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
    int batchInsert(List<SubjectMultiple> subjectMultipleList);

    /**
     * 通过实体作为筛选条件查询
     */
    List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple);
}
