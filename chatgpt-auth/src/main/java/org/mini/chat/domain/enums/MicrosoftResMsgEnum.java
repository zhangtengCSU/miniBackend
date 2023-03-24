package org.mini.chat.domain.enums;

import lombok.Getter;

@Getter
public enum MicrosoftResMsgEnum {
    CODE_503("503","引擎目前过载，请稍后再试"),
    CODE_429("429","发送请求的速度过快。请调整您的请求速度，稍后再试"),
    CODE_400("400","上下文太长咯，双击悬浮球清理一下吧~"),
    CODE_524("524","上下文太长咯，双击悬浮球清理一下吧~"),
    ;

    /**
     * {@link MicrosoftResponseCode}
     */
    private String code;
    private String message;
    MicrosoftResMsgEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMsg(String code) {
        for (MicrosoftResMsgEnum microsoftResMsgEnums : MicrosoftResMsgEnum.values()) {
            if (microsoftResMsgEnums.getCode().equals(code)){
                return microsoftResMsgEnums.getMessage();
            }
        }
        return null;
    }
}
