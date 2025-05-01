package com.club.subject.service.impl;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.service.SubjectCategoryService;
import com.club.subject.common.enums.CategroyTypeEnum;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.convert.SubjectCategoryConverter;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()){
            log.info("添加刷题分类入参：{}", subjectCategoryBO);
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    // 查询岗位分类
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> SubjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategoryList(subjectCategoryList);
        if (log.isInfoEnabled()){
            log.info("查询岗位分类参数：{}", SubjectCategoryBOList);
        }
        return SubjectCategoryBOList;
    }

    //更新分类
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()){
            log.info("更新刷题分类入参：{}", subjectCategoryBO);
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int result = subjectCategoryService.update(subjectCategory);
        return result > 0;
    }

    //删除分类
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int result = subjectCategoryService.update(subjectCategory);
        return result > 0;
    }
}
