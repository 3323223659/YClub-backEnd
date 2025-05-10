package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedEnum;
import com.club.auth.domain.convert.AuthUserConverter;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.AuthUser;
import com.club.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Boolean add(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()){
            log.info("注册用户入参：{}", authUserBO);
        }
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        Integer count = authUserService.insert(authUser);
        //建立一个初始的角色关联
        //把当前用户的角色与权限写入redis中
        return count > 0;
    }
}
