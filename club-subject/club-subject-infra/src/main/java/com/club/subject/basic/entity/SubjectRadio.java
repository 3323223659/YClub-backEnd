package com.club.subject.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 单选题信息表(SubjectRadio)实体类
 *
 * @author makejava
 * @since 2025-04-30 00:53:44
 */
@Data
public class SubjectRadio implements Serializable {
    private static final long serialVersionUID = 587159837636887515L;
/**
     * 主键
     */
    private Long id;
/**
     * 题目id
     */
    private Long subjectId;
/**
     * a,b,c,d
     */
    private Integer optionType;
/**
     * 选项内容
     */
    private String optionContent;
/**
     * 是否正确
     */
    private Integer isCorrect;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 修改人
     */
    private String updateBy;
/**
     * 修改时间
     */
    private Date updateTime;

    private Integer isDeleted;

}

