package org.mini.chat.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;
import org.springframework.stereotype.Service;

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

    public Boolean save2Cache(String answer, String openId) {
        return RedisUtil.setString(ANSWER_REDIS_PREFIX + openId, answer);
    }

    public Boolean deleteCache(String openId) {
        return RedisUtil.delete(ANSWER_REDIS_PREFIX + openId);
    }

    public String getFromCache(String openId) {
        return RedisUtil.getString(ANSWER_REDIS_PREFIX + openId);
    }

}
