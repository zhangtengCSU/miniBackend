package org.mini.chat.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2023/2/22 18:39
 * @Author Rookie
 */
@Data
@Builder
public class ChatRequest {
    private String prompt;
    private String bizCode;
}
