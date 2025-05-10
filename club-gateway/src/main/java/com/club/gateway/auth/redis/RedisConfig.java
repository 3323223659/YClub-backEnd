package com.club.gateway.auth.redis;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/09/21:40
 * @Description: Redis配置类用于自定义RedisTemplate的序列化方式，配置JSON格式的序列化器
 */
@Configuration
public class RedisConfig {
    /**
     * 1. 该方法创建一个RedisTemplate实例，用于操作Redis数据库
     * 2. 配置了key和hash key使用String序列化器
     * 3. 配置了value和hash value使用JSON序列化器
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 创建字符串序列化器（用于key的序列化）
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // 配置key的序列化方式（String类型）
        redisTemplate.setKeySerializer(redisSerializer);
        // 配置hash结构的key的序列化方式（String类型）
        redisTemplate.setHashKeySerializer(redisSerializer);
        // 配置value的序列化方式（JSON格式）
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        // 配置hash结构的value的序列化方式（JSON格式）
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }
    /**
     * 创建并配置Jackson JSON序列化器
     *
     * @return 配置好的Jackson2JsonRedisSerializer实例
     *
     * 说明：
     * 1. 该序列化器用于将Java对象序列化为JSON格式存入Redis
     * 2. 配置了ObjectMapper以支持更灵活的JSON处理
     * 3. 设置了类型信息以便反序列化时能正确还原对象类型
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 创建JSON序列化器，支持任意Java对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 创建并配置ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置所有字段（包括私有字段）都可见
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 配置在遇到未知属性时不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 启用默认类型信息，以便反序列化时能正确还原对象类型
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 将配置好的ObjectMapper设置到序列化器中
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}