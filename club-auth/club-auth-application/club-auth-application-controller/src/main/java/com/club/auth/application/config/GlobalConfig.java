package com.club.auth.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/01/19:07
 * @Description: 全局配置
 */

@Configuration
public class GlobalConfig extends WebMvcConfigurationSupport{

    /**
    * @Description: 自定义消息转换器 JSON 序列化/反序列化
    * @Param: [converters]
    * @return: void
    * @Author: yang
    * @Date: 2025/5/1
    */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(mappingJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        //空对象不报错
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //忽略未知属性(为null)则返回给前端
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter.setObjectMapper(mapper);
        return converter;
    }
}
