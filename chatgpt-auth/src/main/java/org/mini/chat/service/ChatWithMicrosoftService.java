package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.ChatRequest;
import org.mini.common.exceptions.GptException;
import org.mini.common.http.ResponseEnum;
import org.mini.common.utils.OkHttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 透传prompt给微软代理，直接接收answer
 * @Date 2023/3/7 12:19
 * @Author Rookie
 */
@Service
@Slf4j
public class ChatWithMicrosoftService {

    public static final String URL_MICRO_CHAT = "https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat";

    public String chat(ChatRequest request) {
        String answer = null;
        try {
            answer = doPostRequest(request.getPrompt(), request.getBizCode());
        } catch (Exception e) {
            throw GptException.build(500,e.getMessage());
        }
        return answer;
    }

    private String doPostRequest(String prompt, String bizCode) {
        // 1.make body params
        Map<String, Object> params = new HashMap<>();
        params.put("msg", prompt);
        // 2.make header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // 3.do request
        return OkHttpUtils.post(URL_MICRO_CHAT, headers, new Gson().toJson(params));
    }
}
