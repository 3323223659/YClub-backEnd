package com.club.subject.application.dto;

import com.club.subject.common.entity.PageInfo;
import com.club.subject.common.entity.PageResult;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息表(SubjectInfoDTO)
 *
 * @author makejava
 * @since 2025-04-30 00:52:14
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {

    private static final long serialVersionUID = 359488274254940580L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类ids
     */
    private List<Integer> categoryIds;
    /**
     * 标签ids
     */
    private List<Integer> labelIds;
    /**
     * 标签名称
     */
    private List<String> labelNames;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签id
     */
    private Long labelId;


}

