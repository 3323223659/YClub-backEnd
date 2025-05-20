package com.club.auth.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.beans.factory.annotation.Value;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/26/22:16
 * @Description: 数据库加密工具类
 */

public class DruidEncryptUtil {

    //公钥
    @Value("${publicKey}")
    private static String publicKey;
    //私钥
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("privateKey:" + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey:" + publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println(encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println(decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String passwordEncrypt = encrypt("1234");
        //Druid的自动解密机制仅适用于密码字段，不会自动解密用户名和IP地址。
        //String usernameEncrypt = encrypt("root");
        //由于连接数据库在解密之前，因此加密ip可以考虑使用Order注解进行执行顺序的修改，这里没有实现。
        //String IpEncrypt = encrypt("192.168.101.130:3306");
        decrypt(passwordEncrypt);
        //decrypt(usernameEncrypt);
        //decrypt(IpEncrypt);
    }

}
