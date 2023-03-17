package org.mini.common.http;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Description
 * @Date 2023/2/18 20:09
 * @Author Rookie
 */
public class GptHttpResponse<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    private GptHttpResponse() {
    }

    public GptHttpResponse(Builder<T> builder) {
        this.code = builder.code;
        this.msg = builder.message;
        this.data = builder.data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T> Builder<T> SUCCEED() {
        Builder<T> builder = new Builder<>();
        builder.code = HttpResponseState.SUCCESS.getState();
        builder.message = HttpResponseState.SUCCESS.getDesc();
        return builder;
    }

    public static <T> Builder<T> SYSTEM_ERROR() {
        Builder<T> builder = new Builder<>();
        builder.code = HttpResponseState.SYSTEM_ERROR.getState();
        builder.message = HttpResponseState.SYSTEM_ERROR.getDesc();
        return builder;
    }

    public static <T> Builder<T> SUCCEED(T data) {
        Builder<T> builder = new Builder<>();
        builder.data = data;
        builder.code = HttpResponseState.SUCCESS.getState();
        builder.message = HttpResponseState.SUCCESS.getDesc();
        return builder;
    }

    public static <T> Builder<T> FAILED(String msg) {
        Builder<T> builder = new Builder<>();
        builder.code = HttpResponseState.FAILED.getState();
        builder.message = msg;
        return builder;
    }

    public static <T> Builder<T> FAILED() {
        Builder<T> builder = new Builder<>();
        builder.code = HttpResponseState.FAILED.getState();
        builder.message = HttpResponseState.FAILED.getDesc();
        return builder;
    }

    public static <T> Builder<T> FAILED(HttpResponseState state, String msg) {
        Builder<T> builder = new Builder<>();
        builder.code = state.getState();
        builder.message = msg;
        return builder;
    }

    public static <T> Builder<T> FAILED(Integer code, String msg) {
        Builder<T> builder = new Builder<>();
        builder.code = code;
        builder.message = msg;
        return builder;
    }


    public static class Builder<T> {
        private Integer code;
        private String message;
        private T data;

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public GptHttpResponse<T> build() {
            return new GptHttpResponse<>(this);
        }
    }

    @Getter
    public enum HttpResponseState {

        /**
         * 自定义状态码
         */
        SUCCESS(200, "success"),
        FAILED(400, "failed"),
        NO_AUTH(401, "unauthorized"),
        PARM_NULL(402, "param is null"),
        PARM_ILLEGAL(403, "illegal param"),
        SYSTEM_ERROR(500, "system error"),
        UPSTREAM_SYS_ERR(501, "upstream system error"),
        TICKET_EXPIRED(600, "ticket expired");
        private final int state;
        private final String desc;

        HttpResponseState(int state, String desc) {
            this.state = state;
            this.desc = desc;
        }

        public static HttpResponseState valueOf(int state) {
            for (HttpResponseState codeEnum : values()) {
                if (codeEnum.getState() == state) {
                    return codeEnum;
                }
            }
            return null;
        }

        public int getState() {
            return state;
        }

        public String getDesc() {
            return desc;
        }

    }
}