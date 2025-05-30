package com.club.subject.basic.service.impl;

import com.club.subject.basic.entity.SubjectMultiple;
import com.club.subject.basic.mapper.SubjectMultipleDao;
import com.club.subject.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务实现类
 *
 * @author makejava
 * @since 2025-04-30 00:53:24
 */
@Service("subjectMultipleService")
public class SubjectMultipleServiceImpl implements SubjectMultipleService {
    @Resource
    private SubjectMultipleDao subjectMultipleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMultiple queryById(Long id) {
        return this.subjectMultipleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple insert(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.insert(subjectMultiple);
        return subjectMultiple;
    }

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple update(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.update(subjectMultiple);
        return this.queryById(subjectMultiple.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMultipleDao.deleteById(id) > 0;
    }

    /**
     * 批量新增数据
     */
    @Override
    public int batchInsert(List<SubjectMultiple> subjectMultipleList) {
        return this.subjectMultipleDao.insertBatch(subjectMultipleList);
    }

    /**
     * 通过实体作为筛选条件查询
     */
    @Override
    public List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple) {
        return this.subjectMultipleDao.queryByCondition(subjectMultiple);
    }
}
