package com.club.auth.application.controller;

import com.club.auth.application.convent.AuthRoleDTOConverter;
import com.club.auth.application.convent.AuthUserDTOConverter;
import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.application.dto.AuthUserDTO;
import com.club.auth.common.entity.Result;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/13/15:07
 * @Description: 角色管理
 */

@Slf4j
@RestController
@RequestMapping("/role")
public class AuthRoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;
    @PostMapping("/add")
    public Result add(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增角色入参：{}", authRoleDTO);
            }

            Preconditions.checkNotNull(authRoleDTO.getRoleName(),"角色名称不能为空");
            Preconditions.checkNotNull(authRoleDTO.getRoleKey(),"角色key不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToAuthRoleBo(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e) {
            log.error("新增角色异常：{}", e.getMessage(),e);
            return Result.fail("新增角色失败：" + e);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("修改角色入参：{}", authRoleDTO);
            }

            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToAuthRoleBo(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        }catch (Exception e) {
            log.error("修改角色信息异常：{}", e.getMessage(),e);
            return Result.fail("修改角色信息失败：" + e);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除角色入参：{}", authRoleDTO);
            }

            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToAuthRoleBo(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e) {
            log.error("删除角色信息异常：{}", e.getMessage(),e);
            return Result.fail("删除角色信息失败：" + e);
        }
    }

}
