package org.mini.chat;

import lombok.extern.slf4j.Slf4j;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.deprecated.token.TokenProcessService;
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
}
