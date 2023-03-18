package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.enums.BizIdEnum;
import org.mini.chat.domain.request.ChatRequest;
import org.mini.chat.domain.request.MsgObject;
import org.mini.chat.domain.request.QueryModelRequest;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.redis.JedisUtil;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Date 2023/3/8 17:37
 * @Author Rookie
 */
@Data
@Slf4j
public class ModelChatThread implements Callable<String> {

    public static final String BACKEND_URL = "https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat?code=";
    public static final String ANSWER_REDIS_PREFIX = "Answer:";


    private String bizCode;
    private String openId;
    private String requestId;
    private Object prompt;
    private String key4Body;


    ModelChatThread(ChatRequest request) {
        this.bizCode = request.getBiz_id();
        this.openId = request.getOpen_id();

        String bizId = request.getBiz_id();
        switch (bizId) {
            case BizIdEnum.CHAT:
                this.key4Body = "msg";
                this.prompt = request.getChat_prompt();
                break;
            case BizIdEnum.WORD_STORY:
                this.key4Body = "words";
                this.prompt = request.getWord_story_prompt();
                break;
            default:
        }
    }

    @Override
    public String call() throws Exception {
        String res = null;
        // 1.make body params
        Map<String, Object> params = new HashMap<>();
        params.put(this.key4Body, this.prompt);
        // 2.make header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // 3.do request
        String code = RedisUtil.getString("stdServerCode");
        res = OkHttpUtils.post(BACKEND_URL + code, headers, new Gson().toJson(params));
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
