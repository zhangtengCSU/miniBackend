package org.mini.chat.domain.response;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2023/3/19 23:27
 * @Author Rookie
 */
@Data
@Builder
public class ChatResponseFromModelDTO {
    private String code;
    private String data;
    private String message;
}
