package com.club.subject.service.impl;

import com.club.subject.basic.entity.SubjectCategory;
import com.club.subject.basic.entity.SubjectLabel;
import com.club.subject.basic.entity.SubjectMapping;
import com.club.subject.basic.service.SubjectLabelService;
import com.club.subject.basic.service.SubjectMappingService;
import com.club.subject.common.enums.IsDeletedEnum;
import com.club.subject.convert.SubjectCategoryConverter;
import com.club.subject.convert.SubjectLabelConverter;
import com.club.subject.entity.SubjectLabelBO;
import com.club.subject.service.SubjectLabelDomainService;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public void add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("新增标签入参：{}", subjectLabelBO);
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        subjectLabelService.insert(subjectLabel);
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("修改标签入参：{}", subjectLabelBO);
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("删除标签入参：{}", subjectLabelBO);
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("根据分类查询标签入参：{}", subjectLabelBO);
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(subjectMappingList)){
            return Collections.emptyList();
        }
        List<Long> labelIds = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryByIds(labelIds);
        List<SubjectLabelBO> subjectLabelBOList = new LinkedList<>();
        for (SubjectLabel subjectLabel : subjectLabelList) {
            SubjectLabelBO labelBO = SubjectLabelConverter.INSTANCE.convertLabelToBO(subjectLabel);
            labelBO.setCategoryId(categoryId);
            subjectLabelBOList.add(labelBO);
        }
        return subjectLabelBOList;
    }
}
