package org.mini.chat;

import lombok.extern.slf4j.Slf4j;
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
    TokenProcessService tokenProcessService;
    @Test
    void testChat() {
        String prompt = "讲个笑话吧";
        log.info(tokenProcessService.countToken(prompt).toString());
        log.info(chatWithModelService.chat(prompt));
    }
}
