package org.mini.chat;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mini.chat.domain.request.ChatRequest;
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
    @Test
    void testRedis(){
        chatWithMicrosoftService.callModelAsync(ChatRequest.builder().request_id("111").biz_id("2").open_id("asd").times("0").build());
    }

    @Test
    void set() {
//        RedisUtil.setStringExpiredDay("testCode","",999999999);
        ChatWithModelDTO build = ChatWithModelDTO.builder().code("200").data("你好，请问有什么可以帮助你的！").message(null).build();
        log.info(JSONUtil.toJsonStr(build));
    }

}
