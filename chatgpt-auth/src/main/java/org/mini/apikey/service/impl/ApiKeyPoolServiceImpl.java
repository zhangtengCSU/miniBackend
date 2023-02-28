package org.mini.apikey.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mini.apikey.service.ApiKeyPoolService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2023/2/23 14:51
 * @Author Rookie
 */
@Service
@Slf4j
public class ApiKeyPoolServiceImpl implements ApiKeyPoolService {
    @Override
    public String selectKey() {
        return "pK8M54hxVRJM2dhnMv4bT3BlbkFJGRsL4QtBoNt1KZvEezRS";
    }
}
