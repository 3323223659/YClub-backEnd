package com.club.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)BO类
 *
 * @author makejava
 * @since 2025-05-13 21:06:17
 */
@Data
public class AuthRolePermissionBO implements Serializable {
    private static final long serialVersionUID = -16956495872916865L;

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限ids
     */
    private List<Long> permissionIdList;

}

