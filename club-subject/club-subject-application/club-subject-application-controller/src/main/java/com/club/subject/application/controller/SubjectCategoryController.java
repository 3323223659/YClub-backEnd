package com.club.subject.application.controller;

import com.club.subject.application.convert.SubjectCategoryDTOConverter;
import com.club.subject.application.convert.SubjectInfoDTOConverter;
import com.club.subject.application.convert.SubjectLabelDTOConverter;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.common.entity.Result;
import com.club.subject.entity.SubjectCategoryBO;
import com.club.subject.service.SubjectCategoryDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

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
    public Result add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加刷题分类入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父级分类不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("添加刷题分类异常：{}", e.getMessage(),e);
            return Result.fail("新增分类失败：" + e);
        }

    }

    @PostMapping("/queryPrimaryCategory")
    public Result queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("查询岗位大类入参：{}", subjectCategoryDTO);
            }

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList  = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBOToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询岗位大类：{}", e.getMessage(),e);
            return Result.fail("查询分类失败：" + e);
        }
    }

    @PostMapping("/queryCategoryByPrimary")
    public Result queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("根据岗位大类查询小类入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"岗位大类ID不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList  = subjectCategoryDomainService.queryCategory(subjectCategoryBO);

            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBOToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询岗位大类：{}", e.getMessage(),e);
            return Result.fail("查询分类失败：" + e);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("修改分类入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类ID不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean result  = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("修改分类：{}", e.getMessage(),e);
            return Result.fail("修改分类失败：" + e);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除分类入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类ID不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean result  = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除分类：{}", e.getMessage(),e);
            return Result.fail("删除分类失败：" + e);
        }
    }

    @PostMapping("/queryCategoryAndLabel")
    public Result queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("一次性查询分类及标签入参：{}", subjectCategoryDTO);
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类ID不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList  = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);

            List<SubjectCategoryDTO> subjectCategoryDTOList = new LinkedList<>();
            subjectCategoryBOList.forEach(categoryBO -> {
                SubjectCategoryDTO categoryDTO = SubjectCategoryDTOConverter.INSTANCE.convertBoToCategoryDTO(categoryBO);
                categoryDTO.setLabelDTOList(SubjectLabelDTOConverter.INSTANCE.convertBOToLabelDTOList(categoryBO.getLabelBOList()));
                subjectCategoryDTOList.add(categoryDTO);
            });
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("一次性查询分类及标签：{}", e.getMessage(),e);
            return Result.fail("一次性查询分类及标签失败：" + e);
        }
    }

}
