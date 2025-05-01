package com.club.subject.service.impl;

import com.club.subject.basic.entity.SubjectInfo;
import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.basic.entity.SubjectMapping;
import com.club.subject.basic.service.SubjectInfoService;
import com.club.subject.basic.service.SubjectLabelService;
import com.club.subject.basic.service.SubjectMappingService;
import com.club.subject.common.entity.PageResult;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.convert.SubjectInfoConverter;
import com.club.subject.convert.SubjectLabelConverter;
import com.club.subject.entity.SubjectInfoBO;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.entity.SubjectOptionBO;
import com.club.subject.handler.Subject.SubjectTypeHandler;
import com.club.subject.handler.Subject.SubjectTypeHandlerFactory;
import com.club.subject.service.SubjectInfoDomainService;
import com.club.subject.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/20:37
 * @Description:
 */

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()){
            log.info("新增题目入参：{}", subjectInfoBO);
        }

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());

        //如果都写在主流程：判断题目类型并且调用各自的添加方法，会影响主流程的逻辑，所以分开写，即工厂加策略模式
        //一个工厂包含四种类型，根据传入的type自动映射到对应的策略，然后调用对应的策略方法
        //传入类型，根据类型获取对应的handler处理器，然后调用对应的add方法进行此题目类型的新增
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getSubjectTypeHandler(subjectInfoBO.getSubjectType());
        subjectTypeHandler.add(subjectInfoBO);

        //插入mapping表(记录题目的分类与标签)
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        //多对多关系(一个题目对应一个分类下的一个标签),因此需要嵌套遍历
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);

    }

    //分页查询题目列表
    @Override
    public PageResult<SubjectInfoBO> getPage(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()){
            log.info("分页查询题目列表入参：{}", subjectInfoBO);
        }

        //封装分页参数
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();

        //查询总条数
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo,subjectInfoBO.getCategoryId(),subjectInfoBO.getLabelId());
        pageResult.setTotal(count);
        if (count == 0){
            return pageResult;
        }

        //查询分页数据
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo,subjectInfoBO.getCategoryId(),subjectInfoBO.getLabelId(),start,subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertInfoListToBOList(subjectInfoList);
        subjectInfoBOList = subjectInfoBOList.stream().map(BO -> {
            BO.setPageNo(subjectInfoBO.getPageNo());
            BO.setPageSize(subjectInfoBO.getPageSize());
            return BO;
        }).collect(Collectors.toList());
        pageResult.setRecords(subjectInfoBOList);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()){
            log.info("分页查询题目列表入参：{}", subjectInfoBO);
        }

        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());

        //根据题目类型获取对应的处理器拿到对应的answer或者options
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getSubjectTypeHandler(subjectType);
        SubjectOptionBO subjectOptionBO = subjectTypeHandler.querySubjectInfo(subjectInfoBO.getId());
        SubjectInfoBO resultBO = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBO(subjectOptionBO, subjectInfo);

        //查询标签名称
        List<String> labelNameList = new LinkedList<>();
        labelNameList = subjectMappingService.queryLabelName(resultBO.getId());
        resultBO.setLabelNames(labelNameList);

        return resultBO;
    }
}
