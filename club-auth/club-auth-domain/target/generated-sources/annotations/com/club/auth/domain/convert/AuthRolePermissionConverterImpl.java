package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthRolePermissionBO;
import com.club.auth.infra.basic.entity.AuthRolePermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T21:03:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_451 (Oracle Corporation)"
)
public class AuthRolePermissionConverterImpl implements AuthRolePermissionConverter {

    @Override
    public AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO) {
        if ( authRolePermissionBO == null ) {
            return null;
        }

        AuthRolePermission authRolePermission = new AuthRolePermission();

        authRolePermission.setId( authRolePermissionBO.getId() );
        authRolePermission.setRoleId( authRolePermissionBO.getRoleId() );

        return authRolePermission;
    }

    @Override
    public AuthRolePermissionBO convertEntityToBO(AuthRolePermission authRolePermission) {
        if ( authRolePermission == null ) {
            return null;
        }

        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();

        authRolePermissionBO.setId( authRolePermission.getId() );
        authRolePermissionBO.setRoleId( authRolePermission.getRoleId() );

        return authRolePermissionBO;
    }
}
