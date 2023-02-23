package org.mini.chat.domain;

import lombok.Data;

/**
 * @Description
 * @Date 2023/2/17 20:17
 * @Author Rookie
 */@Data
public class MiniAuthRequest {
    private String appId;
    private String secret;
    private String jsCode;
}
