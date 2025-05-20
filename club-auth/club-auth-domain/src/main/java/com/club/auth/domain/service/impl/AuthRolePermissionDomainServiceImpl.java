package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.convert.AuthPermissionConverter;
import com.club.auth.domain.convert.AuthRolePermissionConverter;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRolePermissionBO;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.domain.service.AuthRolePermissionDomainService;
import com.club.auth.infra.basic.entity.AuthPermission;
import com.club.auth.infra.basic.entity.AuthRolePermission;
import com.club.auth.infra.basic.service.AuthPermissionService;
import com.club.auth.infra.basic.service.AuthRolePermissionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    @SneakyThrows
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        for (Long permissionId : authRolePermissionBO.getPermissionIdList()) {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
            rolePermissionList.add(authRolePermission);
        }

        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }

}
