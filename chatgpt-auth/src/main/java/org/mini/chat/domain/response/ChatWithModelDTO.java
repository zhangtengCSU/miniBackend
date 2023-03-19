package org.mini.chat.domain.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Date 2023/3/19 12:55
 * @Author Rookie
 */
@Data
@Builder
public class ChatWithModelDTO {
    private String code;
    private String data;
    private String message;
}
