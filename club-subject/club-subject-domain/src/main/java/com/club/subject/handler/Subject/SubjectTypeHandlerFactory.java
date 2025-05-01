package com.club.subject.handler.Subject;

import com.club.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/30/1:45
 * @Description: 题目类型工厂
 */

@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    public List<SubjectTypeHandler> subjectTypeHandlerList;

    // 题目类型处理器Map
    private final Map<SubjectInfoTypeEnum, SubjectTypeHandler> subjectTypeHandlerMap = new HashMap<>();

    //根据题目类型获取对应的handler处理器
    public SubjectTypeHandler getSubjectTypeHandler(int subjectInfoType) {
        SubjectInfoTypeEnum  subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectInfoType);
        return subjectTypeHandlerMap.get(subjectInfoTypeEnum);
    }

    //初始化工厂Map
    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            SubjectInfoTypeEnum  subjectInfoTypeEnum = subjectTypeHandler.getSubjectInfoType();
            subjectTypeHandlerMap.put(subjectInfoTypeEnum, subjectTypeHandler);
        }
    }
}
