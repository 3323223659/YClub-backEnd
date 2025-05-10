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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user/")
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

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToAnswerBo(authUserDTO);
            return Result.ok(authUserDomainService.add(authUserBO));
        }catch (Exception e) {
            log.error("注册用户异常：{}", e.getMessage(),e);
            return Result.fail("注册用户失败：" + e);
        }
    }

    // 测试登录，浏览器访问： http://localhost:3001/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public SaResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:3001/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
