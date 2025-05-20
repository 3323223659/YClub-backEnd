package com.club.auth.application.controller;

import com.club.auth.application.convent.AuthRoleDTOConverter;
import com.club.auth.application.convent.AuthRolePermissionDTOConverter;
import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.application.dto.AuthRolePermissionDTO;
import com.club.auth.common.entity.Result;
import com.club.auth.domain.convert.AuthRolePermissionConverter;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthRolePermissionBO;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.domain.service.AuthRolePermissionDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/13/15:07
 * @Description: 角色权限管理
 */

@Slf4j
@RestController
@RequestMapping("/rolePermission")
public class AuthRolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;
    @PostMapping("/add")
    public Result add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增角色权限入参：{}", authRolePermissionDTO);
            }

            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(),"角色id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()),"权限关联不能为空");

            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDtoToAuthRolePermissionBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        }catch (Exception e) {
            log.error("新增角色权限异常：{}", e.getMessage(),e);
            return Result.fail("新增角色权限失败：" + e);
        }
    }

}
