package com.club.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.club.auth.application.convent.AuthUserDTOConverter;
import com.club.auth.application.dto.AuthUserDTO;
import com.club.auth.common.entity.Result;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/07/20:58
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class AuthUserController {

    @Resource
    private AuthUserDomainService authUserDomainService;
    @PostMapping("/register")
    public Result register(@RequestBody AuthUserDTO authUserDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("注册用户入参：{}", authUserDTO);
            }

            Preconditions.checkNotNull(authUserDTO.getUserName(),"用户名不能为空");
            Preconditions.checkNotNull(authUserDTO.getPassword(),"密码不能为空");
            Preconditions.checkNotNull(authUserDTO.getEmail(),"邮箱不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAuthUserBo(authUserDTO);
            return Result.ok(authUserDomainService.add(authUserBO));
        }catch (Exception e) {
            log.error("注册用户异常：{}", e.getMessage(),e);
            return Result.fail("注册用户失败：" + e);
        }
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("获取用户信息入参：{}", authUserDTO);
            }

            Preconditions.checkNotNull(authUserDTO.getUserName(),"用户名不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAuthUserBo(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBoToAuthUserDto(userInfo));
        }catch (Exception e) {
            log.error("获取用户信息异常：{}", e.getMessage(),e);
            return Result.fail("获取用户信息失败：" + e);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody AuthUserDTO authUserDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("修改用户信息入参：{}", authUserDTO);
            }

            Preconditions.checkNotNull(authUserDTO.getUserName(),"用户名不能为空");
            Preconditions.checkNotNull(authUserDTO.getPassword(),"密码不能为空");
            Preconditions.checkNotNull(authUserDTO.getEmail(),"邮箱不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAuthUserBo(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e) {
            log.error("修改用户信息异常：{}", e.getMessage(),e);
            return Result.fail("修改用户信息失败：" + e);
        }
    }

    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("启用或禁用用户入参：{}", authUserDTO);
            }

            Preconditions.checkNotNull(authUserDTO.getStatus(),"修改的用户状态不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAuthUserBo(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e) {
            log.error("启用或禁用用户异常：{}", e.getMessage(),e);
            return Result.fail("启用或禁用用户失败：" + e);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody AuthUserDTO authUserDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除用户入参：{}", authUserDTO);
            }

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAuthUserBo(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        }catch (Exception e) {
            log.error("删除用户异常：{}", e.getMessage(),e);
            return Result.fail("删除用户失败：" + e);
        }
    }

    // 测试登录，浏览器访问： http://localhost:3001/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result doLogin(String validCode) {
        try {
            Preconditions.checkNotNull(validCode,"验证码不能为空");
            return Result.ok(authUserDomainService.doLogin(validCode));
        }catch (Exception e){
            log.error("登录异常：{}", e.getMessage(),e);
            return Result.fail("登录失败：");
        }
    }

    @PostMapping("/logOut")
    public Result logOut(@RequestParam String username){
        try {
            log.info("登出用户入参：{}", username);
            Preconditions.checkNotNull(username,"用户名不能为空");

            StpUtil.logout(username);
            return Result.ok();
        }catch (Exception e) {
            log.error("登出用户异常：{}", e.getMessage(),e);
            return Result.fail("登出用户失败：" + e);
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:3001/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
