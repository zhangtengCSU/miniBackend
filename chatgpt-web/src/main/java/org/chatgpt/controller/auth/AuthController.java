package org.chatgpt.controller.auth;

import domain.MiniAuthRequest;
import domain.enums.ResponseEnum;
import exceptions.GptException;
import lombok.extern.slf4j.Slf4j;
import org.chatgpt.domain.GptHttpResponse;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import service.MiniProgramAuthService;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2023/2/12 17:03
 * @Author Rookie
 */
@RestController
@Slf4j
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
}
