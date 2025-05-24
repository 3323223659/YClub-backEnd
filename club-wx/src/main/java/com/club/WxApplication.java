package com.club;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/20/19:14
 * @Description:
 */
@SpringBootApplication
@ComponentScan("com.club")
public class WxApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(WxApplication.class, args);
    }
}
