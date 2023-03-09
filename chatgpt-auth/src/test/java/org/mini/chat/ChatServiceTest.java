package org.mini.chat;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mini.chat.domain.ChatRequest;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.common.utils.RedisUtil;
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
    private ChatWithMicrosoftService chatWithMicrosoftService;
    @Test
    void testRedis() {
        RedisUtil.setString("k1","1");
        RedisUtil.setString("k1","2");
        RedisUtil.setString("k1","3");
        RedisUtil.setString("k1","4");
    }

    @Test
    void call() {
        ChatRequest build = ChatRequest.builder().prompt("你好").bizCode("1").openId("0001").times("0").build();
        chatWithMicrosoftService.callModelAsync(build);
    }
}
