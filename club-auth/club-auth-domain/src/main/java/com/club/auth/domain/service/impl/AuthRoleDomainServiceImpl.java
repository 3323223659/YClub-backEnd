package com.club.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.convert.AuthRoleConverter;
import com.club.auth.domain.convert.AuthUserConverter;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.AuthRole;
import com.club.auth.infra.basic.entity.AuthUser;
import com.club.auth.infra.basic.service.AuthRoleService;
import com.club.auth.infra.basic.service.AuthUserService;
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
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    @SneakyThrows
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertBOToEntity(authRoleBO);

        authRole.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertBOToEntity(authRoleBO);
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        Integer count = authRoleService.update(authRole);
        return count > 0;
    }
}
