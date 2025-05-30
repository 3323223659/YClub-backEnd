package com.club.subject.basic.service.impl;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.mapper.SubjectCategoryDao;
import com.club.subject.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2025-04-26 21:24:12
 */
@Service("subjectCategoryService")
@Slf4j
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 根据id查询
     *
     * @param id 实例对象id
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.insert(subjectCategory);
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.update(subjectCategory);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }

    /**
     * 查询岗位
     *
     * @param subjectCategory 查询条件
     * @return 分类列表
     */
    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.queryCategory(subjectCategory);
    }

    /**
     * 查询岗位数量
     *
     * @param id 岗位id
     * @return 数量
     */
    @Override
    public Integer querySubjectCount(Long id) {
        return this.subjectCategoryDao.querySubjectCount(id);
    }
}
