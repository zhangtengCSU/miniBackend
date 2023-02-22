package org.chatgpt.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.chatgpt.utils.OkHttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/2/22 17:39
 * @Author Rookie
 */
@Service
@Slf4j
public class ChatWithModelService {
    public static final String URL_MINI_CHAT_PREFIX = "https://api.openai.com/v1/";

    public String chat(String prompt) {
        // judge type;
        String type = "completions";
        String apiKey = "UdbWy0cwsnPwnHRzAv90T3BlbkFJX3WdABBivl4CYhrhsRvU";
        Map<String, Object> params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", prompt);
        params.put("temperature", 0);
        params.put("max_tokens", 100);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer sk-" + apiKey);
        String post = OkHttpUtils.post(URL_MINI_CHAT_PREFIX + type, headers, new Gson().toJson(params));
        return post;
    }
}
