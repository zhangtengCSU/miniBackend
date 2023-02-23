package org.mini.chat.domain.enums;

import lombok.Getter;

/**
 * @Description
 * @Date 2023/2/17 12:27
 * @Author Rookie
 */@Getter
public enum WxResponseCodeEnum {
    CODE_NOT_EFFECT(40029,"code无效"),
    TOO_FREQUENT(45011,"api minute-quota reach limit must slower retry next minute"),
    DANGEROUS_USER(40226,"code blocked"),
    SYSTEM_ERROR(-1,"system error")
    ;

    private Integer code;
    private String desc;

    WxResponseCodeEnum(Integer code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
