package org.mini.chat.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2023/3/18 12:04
 * @Author Rookie
 */
@Data
@Builder
public class QueryModelRequest {
    private String biz_id;
    private List<MsgObject> messages;
    private String words;
}
