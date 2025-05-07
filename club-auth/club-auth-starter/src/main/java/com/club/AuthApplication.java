package com.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/07/20:32
 * @Description: 鉴权服务启动类
 */

@SpringBootApplication
@ComponentScan("com.club")
@MapperScan("com.club.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
