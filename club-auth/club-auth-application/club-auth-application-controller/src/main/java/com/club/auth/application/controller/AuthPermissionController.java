package com.club.auth.application.controller;

import com.club.auth.application.convent.AuthPermissionDTOConverter;
import com.club.auth.application.convent.AuthRoleDTOConverter;
import com.club.auth.application.dto.AuthPermissionDTO;
import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.common.entity.Result;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.infra.basic.entity.AuthPermission;
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
@RequestMapping("/permission")
public class AuthPermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;
    @PostMapping("/add")
    public Result add(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增权限入参：{}", authPermissionDTO);
            }

            Preconditions.checkNotNull(authPermissionDTO.getName(),"权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"权限父id不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getType(),"权限类型不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getMenuUrl(),"权限菜单不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getPermissionKey(),"权限key不能为空");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToAuthPermissionBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e) {
            log.error("新增权限异常：{}", e.getMessage(),e);
            return Result.fail("新增权限失败：" + e);
        }
    }

    @PostMapping("/getPermission")
    public Result getPermission(String username){
        try {
            if (log.isInfoEnabled()){
                log.info("查询用户权限信息入参：{}", username);
            }

            Preconditions.checkNotNull(username,"用户名不能为空");

            return Result.ok(authPermissionDomainService.getPermission(username));
        }catch (Exception e) {
            log.error("查询用户权限信息异常：{}", e.getMessage(),e);
            return Result.fail("查询用户权限信息失败：" + e);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("修改权限入参：{}", authPermissionDTO);
            }

            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getName(),"权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"权限父id不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getType(),"权限类型不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getMenuUrl(),"权限菜单不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getPermissionKey(),"权限key不能为空");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToAuthPermissionBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e) {
            log.error("修改权限信息异常：{}", e.getMessage(),e);
            return Result.fail("修改权限信息失败：" + e);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除权限入参：{}", authPermissionDTO);
            }

            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToAuthPermissionBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e) {
            log.error("删除权限色信息异常：{}", e.getMessage(),e);
            return Result.fail("删除权限信息失败：" + e);
        }
    }

}
