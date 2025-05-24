package com.club.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.constant.AuthConstant;
import com.club.auth.domain.convert.AuthUserConverter;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.*;
import com.club.auth.domain.redis.RedisUtil;
import com.club.auth.infra.basic.service.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthRolePermissionService  authRolePermissionService;
    @Resource
    private RedisUtil redisUtil;
    private String salt = "yang";
    private String authPrefixPermission = "auth.permission";
    private String authRolePrefix = "auth.role";
    private static final String LOGIN_PREFIX = "loginCode";

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class) //开启事务
    public Boolean add(AuthUserBO authUserBO) {
        //校验用户是否存在
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        List<AuthUser> authUserList = authUserService.queryByCondition(existAuthUser);
        if (!authUserList.isEmpty()){
            return true;
        }

        AuthUser authUser = AuthUserConverter.INSTANCE.convertBOToEntity(authUserBO);
        //这是非对称加密需要公钥加密私钥解密,数据库密码字段需要长一点
//        HashMap<String, String> keyMap = SaSecureUtil.rsaGenerateKeyPair();
//        String privateKey = keyMap.get("private");club
//        String publicKey = keyMap.get("public");
//        authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey,authUser.getPassword()));
//        String secret = SaSecureUtil.rsaEncryptByPrivate(privateKey, authUser.getPassword());
//        System.out.println(secret);

        //微信登录时没有密码一说
        if (StringUtils.isNotBlank(authUser.getPassword())){
            //该项目用md5配合加盐加密进行密码加密
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(),salt));
        }

        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        Integer count = authUserService.insert(authUser);

        //建立一个初始的角色用户关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        authRole.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        AuthRole authRoleCondition = authRoleService.queryByCondition(authRole);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRoleCondition.getId());
        authUserRole.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        //把当前用户的角色写入redis中
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> authRoleList = new LinkedList<>();
        authRoleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(authRoleList));

        //查询当前用户的角色id列表
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(authUserRole.getRoleId());
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        List<Long> permissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getRoleId).collect(Collectors.toList());

        //根据权限id列表查询权限信息并放入redis
        List<AuthPermission> permissionList = authPermissionService.queryByPermissionIdList(permissionIdList);
        String permissionKey = redisUtil.buildKey(authPrefixPermission, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));

        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //刷新当前用户的信息写入redis中
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //刷新当前用户的信息写入redis中
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String key = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(key);
        if (openId == null){
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.add(authUserBO);
        StpUtil.login(openId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return tokenInfo;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> authUserList = authUserService.queryByCondition(authUser);
        if (authUserList.isEmpty()){
            return new AuthUserBO();
        }
        AuthUser userInfo = authUserList.get(0);
        AuthUserBO userInfoBO = AuthUserConverter.INSTANCE.convertEntityToBO(userInfo);
        return userInfoBO;
    }
}
