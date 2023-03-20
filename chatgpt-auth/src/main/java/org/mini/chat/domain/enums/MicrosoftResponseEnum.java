package org.mini.chat.domain.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum MicrosoftResponseEnum {
    CODE_503("503","引擎目前过载，请稍后再试"),
    CODE_429("429","发送请求的速度过快。请调整您的请求速度，稍后再试")
    ;

    private String code;
    private String message;
    MicrosoftResponseEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMsg(String code) {
        for (MicrosoftResponseEnum microsoftResponseEnums : MicrosoftResponseEnum.values()) {
            if (microsoftResponseEnums.getCode().equals(code)){
                return microsoftResponseEnums.getMessage();
            }
        }
        return null;
    }
}
