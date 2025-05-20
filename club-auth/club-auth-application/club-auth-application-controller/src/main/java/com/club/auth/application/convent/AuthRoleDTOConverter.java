package com.club.auth.application.convent;

import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.application.dto.AuthUserDTO;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthUserBO;
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
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDtoToAuthRoleBo(AuthRoleDTO authRoleDTO);

}
