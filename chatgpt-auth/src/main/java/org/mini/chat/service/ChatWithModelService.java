package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mini.apikey.service.ApiKeyPoolService;
import org.mini.chat.domain.completions.CompletionsResponseFromModel;
import org.mini.common.exceptions.GptException;
import org.mini.common.http.ResponseEnum;
import org.mini.common.utils.OkHttpUtils;
import org.mini.model.service.ModelGptService;
import org.mini.token.TokenProcessService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Resource
    private TokenProcessService tokenProcessService;

    public static final String URL_MINI_CHAT_PREFIX = "https://api.openai.com/v1/";

    public String chat(String prompt) {
        String answer = null;
        // 0.check not null
        if (!StringUtils.hasText(prompt)) {
            throw GptException.build(ResponseEnum.PROMPT_IS_NULL);
        }
        // 1.match type;
        String type = modelGptService.matchModelType();
        // 2.select apiKey
        String apiKey = apiKeyPoolService.selectKey();
        // 3.do post
        CompletionsResponseFromModel completionsResponseFromModel =
                new Gson().fromJson(doPostRequest(prompt, apiKey, type), CompletionsResponseFromModel.class);
        try {
            answer = completionsResponseFromModel.getChoices().get(0).getText();
        } catch (NullPointerException e) {
            return null;
        }
        return answer;
    }

    private String doPostRequest(String prompt, String apiKey, String type) {
        // 0.calculate
        Integer maxToken = tokenProcessService.countToken(prompt);
        // 1.make body params
        Map<String, Object> params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", prompt);
        params.put("temperature", 0);
        params.put("max_tokens", maxToken);
        // 2.make header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer sk-" + apiKey);
        // 3.do request
        String post = OkHttpUtils.post(URL_MINI_CHAT_PREFIX + type, headers, new Gson().toJson(params));

        return post;
    }
}
