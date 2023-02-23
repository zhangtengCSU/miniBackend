package org.mini.chat.controller.chat;

import lombok.extern.slf4j.Slf4j;
import org.mini.common.http.BaseController;
import org.mini.chat.domain.ChatRequest;
import org.mini.common.http.GptHttpResponse;
import org.mini.chat.service.ChatWithModelService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2023/2/12 17:03
 * @Author Rookie
 */
@RequestMapping("/chat")
@RestController
@Slf4j
public class ChatController extends BaseController {

    @Override
    protected Logger getLog() {
        return log;
    }

    @Resource
    private ChatWithModelService chatWithModelService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public GptHttpResponse<String> chatWithModel(@RequestBody ChatRequest request) {
        return dealWithException(request.getPrompt(), chatWithModelService::chat, "call gpt model");
    }
}
