package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.infra.basic.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T21:03:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_451 (Oracle Corporation)"
)
public class AuthRoleConverterImpl implements AuthRoleConverter {

    @Override
    public AuthRole convertBOToEntity(AuthRoleBO authRoleBO) {
        if ( authRoleBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBO.getId() );
        authRole.setRoleName( authRoleBO.getRoleName() );
        authRole.setRoleKey( authRoleBO.getRoleKey() );

        return authRole;
    }

    @Override
    public AuthRoleBO convertEntityToBO(AuthRole authRole) {
        if ( authRole == null ) {
            return null;
        }

        AuthRoleBO authRoleBO = new AuthRoleBO();

        authRoleBO.setId( authRole.getId() );
        authRoleBO.setRoleName( authRole.getRoleName() );
        authRoleBO.setRoleKey( authRole.getRoleKey() );

        return authRoleBO;
    }
}
