package org.mini.chat.controller.chat;

import lombok.extern.slf4j.Slf4j;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.common.http.BaseController;
import org.mini.chat.domain.ChatRequest;
import org.mini.common.http.GptHttpResponse;
import org.mini.chat.service.ChatWithModelService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private ChatWithMicrosoftService chatWithMicrosoftService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public GptHttpResponse<String> chatWithModel(@RequestBody ChatRequest request, @RequestHeader("X-WX-OPENID") String openId) {
        request.setOpenId(openId);
        return dealWithException(request, chatWithMicrosoftService::callModelAsync, "call gpt model");
    }
}
