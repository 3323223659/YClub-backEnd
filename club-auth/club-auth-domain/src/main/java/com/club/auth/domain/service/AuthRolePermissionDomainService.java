package com.club.auth.domain.service;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRolePermissionBO;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:35
 * @Description:
 */

public interface AuthRolePermissionDomainService {

    Boolean add(AuthRolePermissionBO authRolePermissionBO);
}
