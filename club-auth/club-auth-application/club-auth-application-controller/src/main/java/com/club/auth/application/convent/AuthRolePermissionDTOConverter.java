package com.club.auth.application.convent;

import com.club.auth.application.dto.AuthPermissionDTO;
import com.club.auth.application.dto.AuthRolePermissionDTO;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRolePermissionBO;
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
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDtoToAuthRolePermissionBO(AuthRolePermissionDTO authRolePermissionDTO);

}
