package org.mini.chat.domain.response;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2023/3/18 11:58
 * @Author Rookie
 */
@Data
@Builder
public class ChatResponse {
    private String msg;
    private String code;
}
