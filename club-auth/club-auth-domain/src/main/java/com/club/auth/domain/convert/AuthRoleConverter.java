package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.infra.basic.entity.AuthRole;
import com.club.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:40
 * @Description:
 */

@Mapper
public interface AuthRoleConverter {

    AuthRoleConverter INSTANCE = Mappers.getMapper(AuthRoleConverter.class);

    AuthRole convertBOToEntity(AuthRoleBO authRoleBO);

    AuthRoleBO convertEntityToBO(AuthRole authRole);

}
