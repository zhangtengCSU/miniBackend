package org.mini.chat;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
    void testRedis() {
        cacheService.save2Cache("x1","x1","x1");
    }

    @Test
    void set() {
        RedisUtil.setStringExpiredDay("stdServerCode","o7x86aa-RTJZFlrpIrDW4SI9cQ4Yv30VQntIeqWo4PESAzFuKc4ctQ==",999999999);
    }

}
