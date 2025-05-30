package com.club.auth.domain.service;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.club.auth.domain.entity.AuthUserBO;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

public interface AuthUserDomainService {

    Boolean add(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
