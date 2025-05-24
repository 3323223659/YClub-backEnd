package com.club.wx.handler;

import com.club.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/20:21
 * @Description: 接收到文本消息处理
 */
@Component
@Slf4j
public class ReceiveMsgHandler implements WxChatMsgHandler{

    @Resource
    private RedisUtil redisUtil;

    private static final String KEY_WORD = "验证码";

    private static final String LOGIN_PREFIX = "loginCode";
    @Override
    public WxChatMsgTypeEnum getWxChatMsgType() {
        return WxChatMsgTypeEnum.TEXT;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("接收到文本消息");
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        String content = messageMap.get("Content");
        if (KEY_WORD.equals(content)){
            Random random = new Random();
            // 生成000000-999999之间的随机数
            int randomNumber = random.nextInt(1000000); // 生成0-999999的随机数
            // 如果需要保证6位数格式（前面补零）
            String formattedRandom = String.format("%06d", randomNumber);

            // 放入redis中并且设置五分钟有效
            String key = redisUtil.buildKey(LOGIN_PREFIX, formattedRandom);
            redisUtil.setNx(key, fromUserName, 5L, TimeUnit.MINUTES);

            String replyMessage = "验证码为:" + formattedRandom + ",五分钟内有效!";
            String result = "<xml>\n" +
                    "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[ "+ replyMessage +"]]></Content>\n" +
                    "</xml>";
            return result;
        } else {
            return null;
        }
    }
}
