package com.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.club.gateway.auth.redis.RedisUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    // 1,直接与数据库交互 （请求数据量大会对数据库产生压力）
    // 2,redis缓存获取信息（要注重数据同步,该项目用这种方法）
    // 3,先从缓存拿,拿不到的话就查询数据库 （也不错,但要保证缓存击穿、雪崩等问题,不过这里用第二种尝试数据同步）
    @Resource
    private RedisUtil redisUtil;
    private String authPrefixPermission = "auth.permission";
    private String authRolePrefix = "auth.role";

    // 返回此 loginId 拥有的权限列表
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> authPermissionList = getAuthList(loginId.toString(), authPrefixPermission);
        return authPermissionList;
    }

    // 返回此 loginId 拥有的角色列表
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> authRoleList = getAuthList(loginId.toString(), authRolePrefix);
        return authRoleList;
    }

    private List<String> getAuthList(String loginId, String prefix) {
        String authKey = redisUtil.buildKey(prefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if (authValue != null) {
            return Collections.emptyList();
        }
        // 从redis获取数据并且转为list
        List<String> permissionList = new Gson().fromJson(authValue, List.class);
        return permissionList;
    }

}
