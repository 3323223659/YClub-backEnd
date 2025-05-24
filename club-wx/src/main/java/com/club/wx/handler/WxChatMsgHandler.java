package com.club.wx.handler;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/20:19
 * @Description: 微信消息处理接口
 */

public interface WxChatMsgHandler {

    WxChatMsgTypeEnum  getWxChatMsgType();

    String dealMsg(Map<String, String> messageMap);

}
