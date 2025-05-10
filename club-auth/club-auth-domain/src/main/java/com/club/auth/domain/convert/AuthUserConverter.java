package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:40
 * @Description:
 */

@Mapper
public interface AuthUserConverter {

    AuthUserConverter INSTANCE = Mappers.getMapper(AuthUserConverter.class);

    AuthUser convertBOToEntity(AuthUserBO subjectInfoBO);

    AuthUserBO convertInfoToBO(AuthUser subjectInfo);

    List<AuthUserBO> convertInfoListToBOList(List<AuthUser> subjectInfoList);

}
