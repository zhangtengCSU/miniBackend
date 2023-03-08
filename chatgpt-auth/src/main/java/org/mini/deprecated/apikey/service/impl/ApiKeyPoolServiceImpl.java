package org.mini.deprecated.apikey.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mini.deprecated.apikey.service.ApiKeyPoolService;
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
        return "v9mlxQEgcr1wa1pWCUh5T3BlbkFJjkVpdLdZPxyUPmpqPAIT";
    }
}
