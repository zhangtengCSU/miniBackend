package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.ChatRequest;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.exceptions.GptException;
import org.mini.common.redis.JedisUtil;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Date 2023/3/8 17:37
 * @Author Rookie
 */
@Data
@Slf4j
public class ModelChatThread implements Callable<String> {
    @Resource
    private CacheService cacheService;

    public static final String URL_MICRO_CHAT = "https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat";
    public static final String ANSWER_REDIS_PREFIX = "Answer:";

    private String prompt;
    private String bizCode;
    private String openId;
    private String requestId;


    ModelChatThread(String prompt, String bizCode, String openId, String requestId) {
        this.prompt = prompt;
        this.bizCode = bizCode;
        this.openId = openId;
        this.requestId = requestId;
    }

    @Override
    public String call() throws Exception {
        String res = null;
        // 1.make body params
        Map<String, Object> params = new HashMap<>();
        params.put("msg", prompt);
        params.put("bizCode", bizCode);
        // 2.make header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // 3.do request
        res = OkHttpUtils.post(URL_MICRO_CHAT, headers, new Gson().toJson(params));
        log.info("answer:{}", res);
//        Boolean aBoolean = cacheService.save2Cache(res, openId, requestId);
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String setex = jedis.setex(ANSWER_REDIS_PREFIX + openId + ":" + requestId, 60, res);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        log.info("child thread finished");
        return res;
    }
}
