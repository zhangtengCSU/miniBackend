package org.mini.chat.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2023/2/22 18:39
 * @Author Rookie
 */
@Data
@Builder
public class ChatRequest {
    private List<MsgObject> prompt;
    private String bizCode;
    private String times;
    private String requestId;
    /**
     * not necessary
     */
    private String openId;
}
