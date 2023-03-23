package org.mini.chat;


import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mini.chat.controller.chat.ChatController;
import org.mini.chat.domain.request.ChatRequest;
import org.mini.chat.domain.request.MsgObject;
import org.mini.chat.domain.response.ChatResponse;
import org.mini.chat.domain.response.ChatResponseFromModelDTO;
import org.mini.chat.domain.response.ChatWithModelDTO;
import org.mini.chat.service.ChatWithMicrosoftService;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.utils.OkHttpUtils;
import org.mini.common.utils.RedisUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final String BACKEND_URL_TEST = "https://gpt-test.azurewebsites.net/api/chatgpt_for_wechat?code=";
    @Test
    void testRedis(){
        chatWithMicrosoftService.callModelAsync(ChatRequest.builder().request_id("111").biz_id("2").open_id("asd").times("0").build());
    }

    @Test
    void set() throws InterruptedException {
//        RedisUtil.setStringExpiredDay("cfCode","",999999999);
        MsgObject build1 = MsgObject.builder().role("user").content("你好").build();
        List<MsgObject> msg = new ArrayList<>();
        msg.add(build1);
        ChatRequest build = ChatRequest.builder().times("0").request_id("xx12").open_id("xxx").biz_id("1").chat_prompt(msg).build();
        chatWithMicrosoftService.callModelAsync(build);
    }

}
