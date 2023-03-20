package org.mini.chat.controller.chat;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.response.ChatResponse;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.common.http.BaseController;
import org.mini.chat.domain.request.ChatRequest;
import org.mini.common.http.GptHttpResponse;
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
    private ChatWithMicrosoftService chatWithMicrosoftService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public GptHttpResponse<ChatResponse> chatWithModel(@RequestBody ChatRequest request, @RequestHeader("X-WX-OPENID") String openId) {
        request.setOpen_id(openId);
        if (request.getTimes().equals("0")) {
            log.info("【Request】: {}", JSONUtil.toJsonStr(request));
        }
        return dealWithException(request, chatWithMicrosoftService::callModelAsync, "call gpt model");
    }
}
