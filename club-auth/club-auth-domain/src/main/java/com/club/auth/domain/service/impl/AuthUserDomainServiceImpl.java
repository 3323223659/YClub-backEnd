package com.club.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.convert.AuthUserConverter;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.AuthUser;
import com.club.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;
    private String salt = "yang";

    @Override
    @SneakyThrows
    public Boolean add(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()){
            log.info("注册用户入参：{}", authUserBO);
        }
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBOToEntity(authUserBO);

        //这是非对称加密需要公钥加密私钥解密,数据库密码字段需要长一点
//        HashMap<String, String> keyMap = SaSecureUtil.rsaGenerateKeyPair();
//        String privateKey = keyMap.get("private");
//        String publicKey = keyMap.get("public");
//        authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey,authUser.getPassword()));
//        String secret = SaSecureUtil.rsaEncryptByPrivate(privateKey, authUser.getPassword());
//        System.out.println(secret);

        //该项目用md5配合加盐加密进行密码加密
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(),salt));

        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        Integer count = authUserService.insert(authUser);
        //建立一个初始的角色关联
        //把当前用户的角色与权限写入redis中
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()){
            log.info("修改用户信息入参：{}", authUserBO);
        }
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //刷新当前用户的信息写入redis中
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()){
            log.info("修改用户信息入参：{}", authUserBO);
        }
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //刷新当前用户的信息写入redis中
        return count > 0;
    }
}
