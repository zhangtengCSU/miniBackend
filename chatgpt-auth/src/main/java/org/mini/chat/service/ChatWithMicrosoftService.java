package org.mini.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.enums.MicrosoftResponseCode;
import org.mini.chat.domain.enums.MicrosoftResponseEnum;
import org.mini.chat.domain.request.ChatRequest;
import org.mini.chat.domain.response.ChatResponse;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.redis.JedisUtil;
import org.mini.common.utils.GptDateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description 透传prompt给微软代理，直接接收answer
 * @Date 2023/3/7 12:19
 * @Author Rookie
 */
@Service
@Slf4j
public class ChatWithMicrosoftService {
    @Resource
    private CacheService cacheService;
    public static final String ANSWER_REDIS_PREFIX = "Answer:";

    public ChatResponse callModelAsync(ChatRequest request) {
        int times = Integer.parseInt(request.getTimes());
        Long startTime = GptDateUtil.currentSystemTimeAsLong();
        // 1.check if call childThread
        if (times == 0) {
            doThreadTask(request);
        }
        // 2.if not,query cache
        while (true) {
            Long nowTime = GptDateUtil.currentSystemTimeAsLong();
//             a. no response
            if (nowTime - startTime > 10 * 1000) {
                if (times == 6) {
                    return ChatResponse.builder().msg("哎呀，服务器满载了，请稍等后再发送，或联系开发者gptplus@163.com反馈一下吧！").code("500").build();
                }
                if (0 <= times && times < 6) {
                    log.info("The request: {} time out for times - {}",request.getRequest_id(),request.getTimes());
                    return null;
                }
            }
            // b. do get cache
            Jedis jedis = null;
            String answer = null;
            try {
                jedis = JedisUtil.getJedis();
                answer = jedis.get(ANSWER_REDIS_PREFIX + request.getOpen_id() + ":" + request.getRequest_id());
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            if (StringUtils.hasText(answer)) {
                if (MicrosoftResponseCode.THROWABLE_ERROR.contains(answer)) {
                    return ChatResponse.builder().msg(MicrosoftResponseEnum.getMsg(answer)).code("500").build();
                } else if (MicrosoftResponseCode.NOT_THROWABLE_ERROR.contains(answer)) {
                    return ChatResponse.builder().msg("哎呀，服务器满载了，请稍等后再发送，或联系开发者gptplus@163.com反馈一下吧！").code("500").build();
                } else {
                    return ChatResponse.builder().msg(answer).code("200").build();
                }
            }
        }
    }

    private void doThreadTask(ChatRequest request) {
        Callable<String> childThread = new ModelChatThread(request);
        FutureTask<String> futureTask = new FutureTask<>(childThread);
        //FutureTask对象作为Thread对象的target创建新的线程
        Thread thread = new Thread(futureTask);
        thread.start();
    }

}
