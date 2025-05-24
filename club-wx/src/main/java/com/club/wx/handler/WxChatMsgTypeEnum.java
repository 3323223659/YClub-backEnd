package com.club.wx.handler;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/20:13
 * @Description:
 */

public enum WxChatMsgTypeEnum {

    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT("text","用户发送的文本消息");


    private String type;
    private String desc;

    WxChatMsgTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static WxChatMsgTypeEnum getWxChatMsgTypeEnum(String type) {
        for (WxChatMsgTypeEnum wxChatMsgTypeEnum : WxChatMsgTypeEnum.values()) {
            if (wxChatMsgTypeEnum.type.equals(type)) {
                return wxChatMsgTypeEnum;
            }
        }
        return null;
    }

}
