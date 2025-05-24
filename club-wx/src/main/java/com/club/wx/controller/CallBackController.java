package com.club.wx.controller;

import com.club.wx.handler.WxChatMsgFactory;
import com.club.wx.handler.WxChatMsgHandler;
import com.club.wx.utils.MessageUtil;
import com.club.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/19:12
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping
public class CallBackController {

    private static final String TOKEN = "yangClub";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    /**
    * @Description: 回调消息校验
    * @Param: []
    * @return: java.lang.String
    * @Author: yang
    * @Date: 2025/5/20
    */
    @GetMapping("callback")
    public String callBack(@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr) {
        log.info("验签参数：signature:{},timestamp:{},nonce:{},echostr:{}",signature,timestamp,nonce,echostr);
        String shaStr = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (shaStr.equals(signature)) {
            return echostr;
        }
        return "unknown";
    }

    /**
    * @Description: 回调消息处理
    * @Param: [requestBody, signature, timestamp, nonce, msgSignature]
    * @return: java.lang.String
    * @Author: yang
    * @Date: 2025/5/20
    */

    /**
     * 关注类型消息
     * <FromUserName><![CDATA[oLZ247fuxv-2U5UjiERn5Rx5Tw3k]]></FromUserName>
     * <CreateTime>1747742897</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[subscribe]]></Event>
     * <EventKey><![CDATA[]]></EventKey>
     * </xml>
     *  发送消息类型消息
     *<FromUserName><![CDATA[oLZ247fuxv-2U5UjiERn5Rx5Tw3k]]></FromUserName>
     * <CreateTime>1747742943</CreateTime>
     * <MsgType><![CDATA[text]]></MsgType>
     * <Content><![CDATA[hello]]></Content>
     * <MsgId>25021663330846087</MsgId>
     * </xml>
    */
    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String msgType = messageMap.get("MsgType");
        String event = messageMap.get("Event") == null ? "" : messageMap.get("Event");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msgType);
        if (!StringUtils.isEmpty(event)){
            stringBuilder.append(".").append(event);
        }
        String msgTypeKey = stringBuilder.toString();
        log.info("消息类型：{}", msgTypeKey);
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getWxChatMsgHandler(msgTypeKey);
        // 可能发过来的信息没有对应的处理类，如图片信息
        if (wxChatMsgHandler == null) {
            return "unknown";
        }
        String replyContent = wxChatMsgHandler.dealMsg(messageMap);
        log.info("处理结果：{}", replyContent);
        return replyContent;
    }



}
