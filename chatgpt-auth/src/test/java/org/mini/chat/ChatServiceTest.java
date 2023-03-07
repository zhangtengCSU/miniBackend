package org.mini.chat;

import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.ChatRequest;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.chat.service.ChatWithModelService;
import org.junit.jupiter.api.Test;
import org.mini.token.TokenProcessService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2023/2/22 23:36
 * @Author Rookie
 */
@SpringBootTest
@Slf4j
public class ChatServiceTest {
    @Resource
    ChatWithModelService chatWithModelService;
    @Resource
    ChatWithMicrosoftService chatWithMicrosoftService;
    @Resource
    TokenProcessService tokenProcessService;
    @Test
    void testChat() {
        String prompt = "讲个笑话吧";
        ChatRequest build = ChatRequest.builder().prompt(prompt).bizCode("1").build();
        log.info(chatWithMicrosoftService.chat(build));
    }
}
