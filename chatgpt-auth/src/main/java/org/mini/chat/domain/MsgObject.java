package org.mini.chat.domain;

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
    private String newsContent;
    private String from;
}
