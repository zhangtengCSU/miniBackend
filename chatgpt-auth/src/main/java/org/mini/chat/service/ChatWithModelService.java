package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mini.apikey.service.ApiKeyPoolService;
import org.mini.chat.domain.completions.ChatCompletionsResponseFromModel;
import org.mini.common.utils.OkHttpUtils;
import org.mini.model.service.ModelGptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private ApiKeyPoolService apiKeyPoolService;
    @Resource
    private ModelGptService modelGptService;

    public static final String URL_MINI_CHAT_PREFIX = "https://api.openai.com/v1/";

    public String chat(String prompt) {
        // 1.match type;
        String type = modelGptService.matchModelType();
        // 2.select apiKey
        String apiKey = apiKeyPoolService.selectKey();
        // 3.do post
        ChatCompletionsResponseFromModel chatCompletionsResponseFromModel = new Gson().fromJson(doPostRequest(prompt, apiKey, type), ChatCompletionsResponseFromModel.class);
        String answer = chatCompletionsResponseFromModel.getChoices().get(0).getText();
        return answer;
    }

    private String doPostRequest(String prompt, String apiKey, String type) {
        // 1.make body params
        Map<String, Object> params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", prompt);
        params.put("temperature", 0);
        params.put("max_tokens", 100);
        // 2.make header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer sk-" + apiKey);
        // 3.do request
        String post = OkHttpUtils.post(URL_MINI_CHAT_PREFIX + type, headers, new Gson().toJson(params));

        return post;
    }
}
