package org.mini.chat.controller.auth;

import org.mini.common.http.BaseController;
import org.mini.common.http.GptHttpResponse;
import org.mini.chat.domain.MiniAuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.mini.chat.service.MiniProgramAuthService;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2023/2/12 17:03
 * @Author Rookie
 */
@RestController
@Slf4j
@RequestMapping("/auth")
@Deprecated
public class AuthController extends BaseController {

    @Override
    protected Logger getLog() {
        return log;
    }

    @Resource
    private MiniProgramAuthService miniProgramAuthService;

    @RequestMapping(value = "/login/wx", method = RequestMethod.GET)
    public GptHttpResponse<String> login4MiniProg(@RequestBody MiniAuthRequest dto) {
        return dealWithException(dto, miniProgramAuthService::auth4Mini, "login for wx mini program");
    }

    @RequestMapping(value = "/login/container", method = RequestMethod.GET)
    public GptHttpResponse<String> login4ContainerInfo(@RequestHeader("X-WX-OPENID") String openId) {
        return GptHttpResponse.SUCCEED(openId).build();
    }
}
