package com.club.subject.service.impl;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.service.SubjectCategoryService;
import com.club.subject.convert.SubjectCategoryConverter;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        subjectCategoryService.insert(subjectCategory);
    }
}
