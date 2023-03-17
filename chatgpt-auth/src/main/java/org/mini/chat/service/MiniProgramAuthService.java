package org.mini.chat.service;

import com.google.gson.Gson;
import org.mini.chat.domain.MiniAuthRequest;
import org.mini.chat.domain.WxAuthResponse;
import org.mini.common.http.ResponseEnum;
import org.mini.common.exceptions.GptException;
import org.mini.common.utils.JwtUtil;
import org.mini.common.utils.OkHttpUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/2/18 19:32
 * @Author Rookie
 */
@Service
public class MiniProgramAuthService {
    public static final String URL_MINI_AUTH = "https://api.weixin.qq.com/sns/jscode2session";

    public String auth4Mini(MiniAuthRequest dto) {
        String url = URL_MINI_AUTH;
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("appid", dto.getAppId());
        queryMap.put("secret", dto.getSecret());
        queryMap.put("js_code", dto.getJsCode());
        queryMap.put("grant_type", "authorization_code");
        String responseStr = OkHttpUtils.get(url, queryMap);
        WxAuthResponse wxAuthResponse = new Gson().fromJson(responseStr, WxAuthResponse.class);
        // check if exist user
        if (!StringUtils.hasText(wxAuthResponse.getOpenid())) {
            throw GptException.build(ResponseEnum.USER_NOT_EXIST);
        }
        // check if bind user
        if (!StringUtils.hasText(wxAuthResponse.getUnionid())) {
            throw GptException.build(ResponseEnum.USER_NOT_BIND);
        }
        // check if login
        if (!StringUtils.hasText(wxAuthResponse.getSession_key())) {
            throw GptException.build(ResponseEnum.USER_NOT_LOGIN);
        }
        // return custom token
        String token = JwtUtil.createToken("user", wxAuthResponse.getOpenid() + wxAuthResponse.getSession_key());
        //save to redis

        return token;
    }
}
