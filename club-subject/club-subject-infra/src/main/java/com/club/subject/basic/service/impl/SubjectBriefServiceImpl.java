package com.club.subject.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.club.subject.basic.entity.SubjectBrief;
import com.club.subject.basic.mapper.SubjectBriefDao;
import com.club.subject.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author makejava
 * @since 2025-04-30 00:52:37
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl implements SubjectBriefService {
    @Resource
    private SubjectBriefDao subjectBriefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectBrief queryById(Long id) {
        return this.subjectBriefDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief insert(SubjectBrief subjectBrief) {
        this.subjectBriefDao.insert(subjectBrief);
        return subjectBrief;
    }

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief update(SubjectBrief subjectBrief) {
        this.subjectBriefDao.update(subjectBrief);
        return this.queryById(subjectBrief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectBriefDao.deleteById(id) > 0;
    }

    /**
     * 批量修改数据
     */
    @Override
    public int batchInsert(List<SubjectBrief> subjectBriefList) {
        return this.subjectBriefDao.insertBatch(subjectBriefList);
    }

    @Override
    public SubjectBrief queryByCondition(SubjectBrief subjectBrief) {
        return this.subjectBriefDao.queryByCondition(subjectBrief);
    }
}
