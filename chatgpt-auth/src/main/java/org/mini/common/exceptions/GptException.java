package org.mini.common.exceptions;

import org.mini.common.http.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Date 2023/2/18 20:43
 * @Author Rookie
 */
@Slf4j
public class GptException extends  RuntimeException{
    private Integer code;
    private String  msg;

    private GptException(){};

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static GptException build(Integer code, String msg) {
        GptException reachException = new GptException();
        reachException.setCode(code);
        reachException.setMsg(msg);
        // error log
        if (ResponseEnum.ERROR.getCode().equals(code)) {
            log.error(msg);
        }
        return reachException;
    }

    public static GptException build(ResponseEnum responseEnum) {
        return build(responseEnum.getCode(), responseEnum.getMsg());
    }

    public static GptException build(ResponseEnum responseEnum, String message) {
        return build(responseEnum.getCode(), message);
    }
}
