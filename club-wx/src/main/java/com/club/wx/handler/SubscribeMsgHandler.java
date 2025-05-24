package com.club.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/20:20
 * @Description: 关注事件处理
 */
@Component
@Slf4j
public class SubscribeMsgHandler implements WxChatMsgHandler{
    @Override
    public WxChatMsgTypeEnum getWxChatMsgType() {
        return WxChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("用户关注事件");
        String replyMessage = "欢迎关注YClub社区!回复验证码获得登录验证码。";
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        String result = "<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[ "+ replyMessage +"]]></Content>\n" +
                "</xml>";
        return result;
    }
}
