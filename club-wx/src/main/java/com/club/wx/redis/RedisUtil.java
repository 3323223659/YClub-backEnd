package com.club.wx.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * @Author: yang
 * @Date: 2025/05/10/22:24
 * @Description: RedisUtil 封装常用Redis操作，简化RedisTemplate的使用
 */
@Slf4j
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate; // 注入RedisTemplate实例

    private static final String CACHE_KEY_SEPARATOR = "."; // 缓存key的分隔符

    /**
     * 构建缓存key
     * @param strObjs 可变参数，用于拼接key的各个部分
     * @return 使用指定分隔符连接后的字符串作为key
     * 示例：buildKey("user", "info", "123") -> "user.info.123"
     */
    public String buildKey(String... strObjs) {
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    /**
     * 判断key是否存在
     * @param key 要检查的key
     * @return true-存在，false-不存在
     */
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除指定的key
     * @param key 要删除的key
     * @return true-删除成功，false-删除失败
     */
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置键值对(不过期)
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置键值对（仅当key不存在时，redis🔒实现）
     * @param key 键
     * @param value 值
     * @param time 过期时间
     * @param timeUnit 时间单位
     * @return true-设置成功，false-设置失败（key已存在）
     */
    public boolean setNx(String key, String value, Long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 获取指定key的值
     * @param key 键
     * @return 对应的值，如果key不存在返回null
     */
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 向有序集合添加元素
     * @param key 集合key
     * @param value 元素值
     * @param score 分数
     * @return true-添加成功，false-添加失败
     */
    public Boolean zAdd(String key, String value, Long score) {
        return redisTemplate.opsForZSet().add(key, value, Double.valueOf(String.valueOf(score)));
    }

    /**
     * 获取有序集合的元素数量
     * @param key 集合key
     * @return 集合元素数量
     */
    public Long countZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取有序集合指定区间的元素
     * @param key 集合key
     * @param start 起始位置(包含)
     * @param end 结束位置(包含)
     * @return 元素集合
     */
    public Set<String> rangeZset(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 从有序集合中移除指定元素
     * @param key 集合key
     * @param value 要移除的元素
     * @return 移除的元素数量
     */
    public Long removeZset(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 从有序集合中批量移除元素
     * @param key 集合key
     * @param value 要移除的元素集合
     */
    public void removeZsetList(String key, Set<String> value) {
        value.stream().forEach((val) -> redisTemplate.opsForZSet().remove(key, val));
    }

    /**
     * 获取有序集合中指定元素的分数
     * @param key 集合key
     * @param value 元素值
     * @return 元素的分数，如果元素不存在返回null
     */
    public Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取有序集合中指定分数区间的元素
     * @param key 集合key
     * @param start 起始分数(包含)
     * @param end 结束分数(包含)
     * @return 元素集合
     */
    public Set<String> rangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(String.valueOf(start)), Double.valueOf(String.valueOf(end)));
    }

    /**
     * 增加有序集合中指定元素的分数
     * @param key 集合key
     * @param obj 元素值
     * @param score 要增加的分数
     * @return 增加后的新分数
     */
    public Object addScore(String key, Object obj, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }

    /**
     * 获取有序集合中指定元素的排名（升序排名，从0开始）
     * @param key 集合key
     * @param obj 元素值
     * @return 排名，如果元素不存在返回null
     */
    public Object rank(String key, Object obj) {
        return redisTemplate.opsForZSet().rank(key, obj);
    }
}