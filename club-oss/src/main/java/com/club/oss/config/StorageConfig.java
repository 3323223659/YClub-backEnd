package com.club.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.club.oss.adapter.StorageAdapter;
import com.club.oss.adapter.AliyunStorageAdapter;
import com.club.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/2:45
 * @Description: 文件存储适配器（根据不同yml配置对应不同oss服务）
 */

@Configuration
@RefreshScope //Spring Cloud提供的一个注解，主要用于动态刷新配置
@EnableAutoConfiguration //启用SpringBoot的自动配置机制(在大多数SpringBoot项目中,其实该注解已在主类中全局启用,无需在单个配置类中重复声明)
public class StorageConfig {

     @Value("${storage.service.type}")
     private String type;

     @Bean
     @RefreshScope
     public StorageAdapter StorageService() {
          if ("aliyun".equals(type)) {
               return new AliyunStorageAdapter();
          } else if ("minio".equals(type)) {
               return new MinioStorageAdapter();
          }else {
               throw new IllegalArgumentException("暂不支持该类型");
          }
     }

}
