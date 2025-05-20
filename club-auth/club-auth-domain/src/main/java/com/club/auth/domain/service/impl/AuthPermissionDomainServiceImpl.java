package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.convert.AuthPermissionConverter;
import com.club.auth.domain.convert.AuthRoleConverter;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.infra.basic.entity.AuthPermission;
import com.club.auth.infra.basic.entity.AuthRole;
import com.club.auth.infra.basic.service.AuthPermissionService;
import com.club.auth.infra.basic.service.AuthRoleService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    @SneakyThrows
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertBOToEntity(authPermissionBO);

        authPermission.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
