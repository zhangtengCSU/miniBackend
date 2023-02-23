package org.mini.common.http;

/**
 * @Description
 * @Date 2023/2/17 20:41
 * @Author Rookie
 */public enum ResponseEnum {

    SUCCESS(200, "success"),

    ERROR(101, "error"),

    PARAM_ERROR(102, "parameter error"),

    UNAUTHORIZED(103, "unauthorized"),

    NOT_FOUND(104, "not found"),

    CUSTOM(105, null),

    WRONG_STATUS(106, "Current Operate Object in Unexpected Status "),


    POOL_REACH_MAX(107, "obey the limitation a pool has no asset"),

    /**
     * USER
     **/
    USER_NOT_EXIST(1000, "The user not Exist"),
    USER_NOT_BIND(1001, "The user has not bind to mini app"),
    USER_NOT_LOGIN(1002, "The user info has not been authorised"),

    TOKEN_EXPIRED(401, "The token was expired"),


    /********************* format ****************************/
    PROMPT_IS_NULL(1003,"prompt is null")


    ;
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
