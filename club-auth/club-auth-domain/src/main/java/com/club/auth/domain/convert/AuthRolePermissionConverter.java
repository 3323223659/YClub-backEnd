package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRolePermissionBO;
import com.club.auth.infra.basic.entity.AuthPermission;
import com.club.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:40
 * @Description:
 */

@Mapper
public interface AuthRolePermissionConverter {

    AuthRolePermissionConverter INSTANCE = Mappers.getMapper(AuthRolePermissionConverter.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);

    AuthRolePermissionBO convertEntityToBO(AuthRolePermission authRolePermission);

}
