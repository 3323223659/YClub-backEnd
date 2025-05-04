package com.club.subject.application.controller;

import com.club.subject.application.convert.SubjectLabelDTOConverter;
import com.club.subject.application.dto.SubjectLabelDTO;
import com.club.subject.common.entity.Result;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.service.SubjectLabelDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/20:05
 * @Description: 标签
 */

@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增标签入参：{}", subjectLabelDTO);
            }

            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(),"标签名称不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"标签的分类ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("新增标签异常：{}", e.getMessage(),e);
            return Result.fail("新增标签失败：" + e);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("修改标签入参：{}", subjectLabelDTO);
            }

            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签ID不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(),"标签名称不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("修改标签异常：{}", e.getMessage(),e);
            return Result.fail("修改标签失败：" + e);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除标签入参：{}", subjectLabelDTO);
            }

            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除标签异常：{}", e.getMessage(),e);
            return Result.fail("删除标签失败：" + e);
        }
    }

    @PostMapping("/queryLabelByCategoryId")
    public Result queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("根据分类id查询标签入参：{}", subjectLabelDTO);
            }

            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBOToLabelBoList(subjectLabelBOList);
            return Result.ok(subjectLabelDTOList);
        } catch (Exception e) {
            log.error("根据分类id查询标签异常：{}", e.getMessage(),e);
            return Result.fail("根据分类id查询标签失败：" + e);
        }
    }

}
