package com.club.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthRole)DTO类
 *
 * @author makejava
 * @since 2025-05-13 15:03:35
 */
@Data
public class AuthRoleDTO implements Serializable {
    private static final long serialVersionUID = -12615955426499491L;

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;
}

