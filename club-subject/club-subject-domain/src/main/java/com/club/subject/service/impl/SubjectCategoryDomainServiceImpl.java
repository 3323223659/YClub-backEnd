package com.club.subject.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.basic.entity.SubjectMapping;
import com.club.subject.basic.service.SubjectCategoryService;
import com.club.subject.basic.service.SubjectLabelService;
import com.club.subject.basic.service.SubjectMappingService;
import com.club.subject.common.enums.CategroyTypeEnum;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.convert.SubjectCategoryConverter;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;

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
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategoryList(subjectCategoryList);
        if (log.isInfoEnabled()){
            log.info("查询岗位分类参数：{}", subjectCategoryBOList);
        }
        subjectCategoryBOList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });

        return subjectCategoryBOList;
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

    //一次性查询分类及标签
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        // 查询当前大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()){
            log.info("查询当前大类下所有分类：{}", subjectCategoryList);
        }
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategoryList(subjectCategoryList);
        // 依次获取标签信息
        subjectCategoryBOList.forEach(bo -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setCategoryId(bo.getId());
            //  获取该分类下题目的所有Mapping信息（去重）
            List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
            if (CollectionUtils.isEmpty(subjectMappingList)){
                return;
            }
            // 取出id字段再批量查询出对应的标签信息（存入SubjectLabelBO的LabelBOList字段中返回）
            List<Long> labelIds = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryByIds(labelIds);
            List<SubjectLabelBO> subjectLabelBOList = new LinkedList<>();
            subjectLabelList.forEach(item -> {
                SubjectLabelBO labelBO = new SubjectLabelBO();
                labelBO.setId(item.getId());
                labelBO.setLabelName(item.getLabelName());
                labelBO.setSortNum(item.getSortNum());
                labelBO.setCategoryId(item.getCategoryId());
                subjectLabelBOList.add(labelBO);
            });
            bo.setLabelBOList(subjectLabelBOList);
        });

        return subjectCategoryBOList;
    }
}
