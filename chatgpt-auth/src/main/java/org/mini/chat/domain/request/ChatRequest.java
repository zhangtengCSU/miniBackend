package org.mini.chat.domain.request;

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

    private String biz_id;
    private String times;
    private String request_id;
    /**
     * not necessary
     */
    private String open_id;
    private List<MsgObject> chat_prompt;
    private String word_story_prompt;
}
