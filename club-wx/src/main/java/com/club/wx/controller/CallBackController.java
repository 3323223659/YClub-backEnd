package com.club.wx.controller;

import com.club.wx.utils.MessageUtil;
import com.club.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好,欢迎关注YClub社区！]]></Content>\n" +
                "</xml>";
        return content;
    }


}
