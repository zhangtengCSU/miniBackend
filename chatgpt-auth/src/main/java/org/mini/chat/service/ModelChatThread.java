package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.Data;
import org.mini.chat.domain.ChatRequest;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.exceptions.GptException;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;

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
public class ModelChatThread implements Callable<String> {
    @Resource
    private CacheService cacheService;

    public static final String URL_MICRO_CHAT = "https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat";

    private String prompt;
    private String bizCode;
    private String openId;

    ModelChatThread(String prompt, String bizCode, String openId) {
        this.prompt = prompt;
        this.bizCode = bizCode;
        this.openId = openId;
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
        save2Cache(res, openId);
        return res;
    }

    private void save2Cache(String answer, String openId) {
        cacheService.save2Cache(answer, openId);
    }
}
