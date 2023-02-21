package service;

import com.google.gson.Gson;
import domain.MiniAuthRequest;
import domain.WxAuthResponse;
import domain.enums.ResponseEnum;
import exceptions.GptException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.JwtUtil;
import utils.OkHttpUtils;

import javax.annotation.Resource;
import java.util.HashMap;

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
        String responseStr = OkHttpUtils.get(url, new HashMap<>(4) {
            {
                put("appid", dto.getAppId());
                put("secret", dto.getSecret());
                put("js_code", dto.getJsCode());
                put("grant_type", "authorization_code");
            }
        });
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
