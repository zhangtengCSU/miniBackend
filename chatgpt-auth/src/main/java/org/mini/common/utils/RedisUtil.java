package org.mini.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private volatile static RedisTemplate redisTemplate;

    private RedisUtil() {
        // no instance
    }


    /**
     * fetch RedisTemplate instance
     */
    private static RedisTemplate fetchRedisTemplate() {
        if (Objects.isNull(redisTemplate)) {
            synchronized (RedisUtil.class) {
                if (Objects.isNull(redisTemplate)) {
                    redisTemplate = SpringUtil.getBean(RedisTemplate.class);
                }
            }
        }
        return redisTemplate;
    }


    /**
     * set string
     */
    public static boolean setString(String key, String value) {
        fetchRedisTemplate().opsForValue().set(key, value, 1800L, TimeUnit.SECONDS);
        return true;
    }

    /**
     * set string
     */
    public static boolean setString(String key, String value, long seconds) {
        fetchRedisTemplate().opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return true;
    }

    public static boolean setStringExpiredDay(String key, String value, long days) {
        fetchRedisTemplate().opsForValue().set(key, value, days, TimeUnit.DAYS);
        return true;
    }


    /**
     * get string
     */
    public static String getString(String key) {
        Object o = fetchRedisTemplate().opsForValue().get(key);
        if (Objects.isNull(o)) return null;
        return o.toString();
    }


    /**
     * delete key
     */
    public static boolean delete(String key) {
        return fetchRedisTemplate().delete(key);
    }


}
