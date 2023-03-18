package org.mini.chat.domain.response;

import lombok.Data;

/**
 * @Description
 * @Date 2023/2/17 20:25
 * @Author Rookie
 */
@Data
public class WxAuthResponse {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
