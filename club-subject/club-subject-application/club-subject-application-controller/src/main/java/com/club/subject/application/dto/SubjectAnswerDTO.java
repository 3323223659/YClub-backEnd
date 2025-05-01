package com.club.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案DTO
 *
 * @author makejava
 * @since 2025-04-30 00:52:14
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = 359488274254940580L;
    /**
     * 答案选项id
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;


}

