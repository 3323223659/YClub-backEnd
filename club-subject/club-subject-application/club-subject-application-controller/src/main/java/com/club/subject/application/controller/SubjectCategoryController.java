package com.club.subject.application.controller;

import com.club.subject.application.convert.SubjectCategoryDTOConverter;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.common.entity.Result;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.service.SubjectCategoryDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:44
 * @Description: 刷题分类
 */

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加刷题分类入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父级分类不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("添加刷题分类异常：{}", e.getMessage(),e);
            return Result.fail();
        }

    }

}
