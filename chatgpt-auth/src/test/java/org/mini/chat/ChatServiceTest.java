package org.mini.chat;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mini.common.utils.RedisUtil;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Date 2023/2/22 23:36
 * @Author Rookie
 */
@SpringBootTest
@Slf4j
public class ChatServiceTest {
    @Test
    void testRedis() {
        log.info(RedisUtil.getString("kty"));
    }
}
