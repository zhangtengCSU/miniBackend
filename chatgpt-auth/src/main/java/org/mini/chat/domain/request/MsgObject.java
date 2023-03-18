package org.mini.chat.domain.request;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2023/3/17 19:53
 * @Author Rookie
 */
@Data
@Builder
public class MsgObject {
    private String content;
    private String role;
}
