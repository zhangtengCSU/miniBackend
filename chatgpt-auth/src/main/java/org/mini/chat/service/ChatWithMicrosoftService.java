package org.mini.chat.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mini.chat.domain.ChatRequest;
import org.mini.chat.service.cache.CacheService;
import org.mini.common.exceptions.GptException;
import org.mini.common.http.ResponseEnum;
import org.mini.common.utils.GptDateUtil;
import org.mini.common.utils.OkHttpUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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

    public String callModelAsync(ChatRequest request) {
        int times = Integer.parseInt(request.getTimes());
        Long startTime = GptDateUtil.currentSystemTimeAsLong();
        // 1.check if call childThread
        if (times == 0) {
            log.info("times = {},开始执行子线程",times);
            doThreadTask(request);
        }
        // 2.if not,query cache
        while (true) {
            Long nowTime = GptDateUtil.currentSystemTimeAsLong();
            // a. no response
            if (nowTime - startTime > 13 * 1000) {
                if (times == 4) {
                    return "给AI问无语了，请联系开发者gptplus@163.com反馈一下吧！";
                }
                if (0 <= times && times < 5) {
                    return null;
                }
            }
            // b. do get cache
            String answer = getFromCache(request.getOpenId());
            if (StringUtils.hasText(answer)) {
                return answer;
            }
        }
    }

    private void doThreadTask(ChatRequest request) {
        Callable<String> childThread = new ModelChatThread(request.getPrompt(), request.getBizCode(), request.getOpenId());
        FutureTask<String> futureTask = new FutureTask<>(childThread);
        //FutureTask对象作为Thread对象的target创建新的线程
        Thread thread = new Thread(futureTask);
        thread.start();
    }

    private String getFromCache(String openId) {
        // 1.get answer
        String fromCache = cacheService.getFromCache(openId);
        log.info("getFromCache:{}",fromCache);
        // 2.if not null,then delete it
        if (StringUtils.hasText(fromCache)) {
            cacheService.deleteCache(openId);
        }
        return fromCache;
    }
}
