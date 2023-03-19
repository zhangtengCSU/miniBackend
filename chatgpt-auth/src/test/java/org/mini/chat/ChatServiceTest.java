package org.mini.chat;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mini.chat.controller.chat.ChatController;
import org.mini.chat.domain.request.ChatRequest;
import org.mini.chat.domain.response.ChatResponse;
import org.mini.chat.domain.response.ChatWithModelDTO;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.chat.service.cache.CacheService;
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
    @Resource
    private CacheService cacheService;
    @Resource
    private ChatController chatController;
    @Test
    void testRedis(){
        chatWithMicrosoftService.callModelAsync(ChatRequest.builder().request_id("111").biz_id("2").open_id("asd").times("0").build());
    }

    @Test
    void set() throws InterruptedException {
//        RedisUtil.setStringExpiredDay("testCode","",999999999);
        ChatRequest build = ChatRequest.builder().times("0").request_id("xx").open_id("xxx").biz_id("1").word_story_prompt("你好").build();
        chatWithMicrosoftService.callModelAsync(build);
        Thread.sleep(10000L);
    }

}
