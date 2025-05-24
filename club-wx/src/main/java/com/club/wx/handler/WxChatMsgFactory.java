package com.club.wx.handler;

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
 * @Date: 2025/05/20/20:22
 * @Description: 微信消息工厂
 */
@Component
public class WxChatMsgFactory implements InitializingBean {

    // 注入所有实现WxChatMsgHandler接口的bean
    @Resource
    private List<WxChatMsgHandler> wxChatMsgHandlers;

    private Map<WxChatMsgTypeEnum, WxChatMsgHandler> wxChatMsgHandlerMap = new HashMap<>();

    public WxChatMsgHandler getWxChatMsgHandler(String msgType) {
        WxChatMsgTypeEnum wxChatMsgTypeEnum = WxChatMsgTypeEnum.getWxChatMsgTypeEnum(msgType);
        return wxChatMsgHandlerMap.get(wxChatMsgTypeEnum);
    }

    // 初始化方法，将所有实现WxChatMsgHandler接口的bean并且找到对应的枚举类型放入map中
    @Override
    public void afterPropertiesSet() throws Exception {
        for (WxChatMsgHandler wxChatMsgHandler : wxChatMsgHandlers) {
            WxChatMsgTypeEnum wxChatMsgTypeEnum = wxChatMsgHandler.getWxChatMsgType();
            wxChatMsgHandlerMap.put(wxChatMsgTypeEnum, wxChatMsgHandler);
        }
    }
}
