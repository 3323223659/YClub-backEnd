package com.club.subject.entity;

import com.club.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfoBO)
 *
 * @author makejava
 * @since 2025-04-30 00:52:14
 */
@Data
public class SubjectOptionBO implements Serializable {
    private static final long serialVersionUID = 359488274254940580L;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;


}

