package org.mini.chat.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.mini.common.redis.JedisUtil;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/3/8 19:34
 * @Author Rookie
 */
@Service
@Slf4j
public class CacheService {
    public static final String ANSWER_REDIS_PREFIX = "Answer:";

    public Boolean save2Cache(String answer, String openId, String requestId) {
        Jedis jedis = JedisUtil.getJedis();
        String setex = jedis.setex(ANSWER_REDIS_PREFIX + openId + ":" + requestId, 60, answer);
        jedis.close();
        log.info(setex);
        return true;
    }

    public Boolean deleteCache(String openId) {
        return RedisUtil.delete(ANSWER_REDIS_PREFIX + openId);
    }

    public String getFromCache(String openId, String requestId) {
        Jedis jedis = JedisUtil.getJedis();
        String res = jedis.get(ANSWER_REDIS_PREFIX + openId + ":" + requestId);
        return res;
    }

}
