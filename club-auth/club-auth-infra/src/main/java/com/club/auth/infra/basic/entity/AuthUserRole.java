package com.club.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户角色表(AuthUserRole)实体类
 *
 * @author makejava
 * @since 2025-05-13 15:39:34
 */
@Data
public class AuthUserRole implements Serializable {
    private static final long serialVersionUID = 789085852839616381L;
/**
     * 主键
     */
    private Long id;
/**
     * 用户id
     */
    private Long userId;
/**
     * 角色id
     */
    private Long roleId;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;

    private Integer isDeleted;

}

