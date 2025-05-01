package com.club.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)DTO类
 *
 * @author makejava
 * @since 2025-04-29 20:11:25
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = -68949625226742898L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private Long categoryId;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;

}

