package org.mini.common.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description Jedis工具类（封装了连接池）
 * @Date 2023/3/9 13:14
 * @Author Rookie
 */
@Slf4j
public class JedisUtil {
    private static JedisPool jedisPool;
    static {
        // 配置连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMinIdle(10);
        config.setMaxIdle(100);
        // 创建连接池
        jedisPool = new JedisPool(config, "43.139.207.55", 6379, 2000, "zt990413");
        log.info("jedis pool init Succeed,if Closed: " + jedisPool.isClosed());
    }
    /**
     * 获取redis连接
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

}
