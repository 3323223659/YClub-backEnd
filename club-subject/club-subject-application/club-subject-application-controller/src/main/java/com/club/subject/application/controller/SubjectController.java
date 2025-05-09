package com.club.subject.application.controller;

import com.club.subject.application.convert.SubjectAnswerDTOConverter;
import com.club.subject.application.convert.SubjectInfoDTOConverter;
import com.club.subject.application.convert.SubjectLabelDTOConverter;
import com.club.subject.application.dto.SubjectInfoDTO;
import com.club.subject.application.dto.SubjectLabelDTO;
import com.club.subject.basic.service.SubjectInfoService;
import com.club.subject.common.entity.PageResult;
import com.club.subject.common.entity.Result;
import com.club.subject.entity.SubjectAnswerBO;
import com.club.subject.entity.SubjectInfoBO;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.service.SubjectInfoDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
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
 * @Date: 2025/04/30/1:01
 * @Description: 题目模块
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增题目入参：{}", subjectInfoDTO);
            }

            Preconditions.checkNotNull(subjectInfoDTO.getSubjectName(),"题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(),"题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(),"题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(),"题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()),"分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()),"标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToInfoBo(subjectInfoDTO);
            //将选项答案转换成BO放入options中
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE.convertListDtoToAnswerBo(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOList);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("新增题目异常：{}", e.getMessage(),e);
            return Result.fail("新增题目失败：" + e);
        }
    }

    @PostMapping("/getSubjectPage")
    public Result getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("分页查询题目列表入参：{}", subjectInfoDTO);
            }

            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(),"分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(),"标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToInfoBo(subjectInfoDTO);
            subjectInfoBO.setPageNo(subjectInfoDTO.getPageNo());
            subjectInfoBO.setPageSize(subjectInfoDTO.getPageSize());
            PageResult<SubjectInfoBO> pageResult = subjectInfoDomainService.getPage(subjectInfoBO);
            return Result.ok(pageResult);
        } catch (Exception e) {
            log.error("分页查询题目列表异常：{}", e.getMessage(),e);
            return Result.fail("分页查询题目列表失败：" + e);
        }
    }

    @PostMapping("/querySubjectInfo")
    public Result querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("查询题目详情入参：{}", subjectInfoDTO);
            }

            Preconditions.checkNotNull(subjectInfoDTO.getId(),"题目id不能为空");

            //查询题目详情
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToInfoBo(subjectInfoDTO);
            SubjectInfoBO result = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO subjectInfoDTOResult = SubjectInfoDTOConverter.INSTANCE.convertBoToInfoDto(result);
            return Result.ok(subjectInfoDTOResult);
        } catch (Exception e) {
            log.error("查询题目详情异常：{}", e.getMessage(),e);
            return Result.fail("查询题目详情失败：" + e);
        }
    }

}
