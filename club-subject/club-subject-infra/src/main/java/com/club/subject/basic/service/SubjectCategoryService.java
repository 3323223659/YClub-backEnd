package com.club.subject.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.club.subject.basic.entity.SubjectCategory;

/**
 * 题目分类(SubjectCategory)表服务接口
 *
 * @author makejava
 * @since 2025-04-26 21:24:11
 */
public interface SubjectCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory insert(SubjectCategory subjectCategory);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
