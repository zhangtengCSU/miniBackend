package org.mini.chat.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2023/3/19 23:27
 * @Author Rookie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseFromModelDTO {
    private String code;
    private String data;
    private String message;
}
