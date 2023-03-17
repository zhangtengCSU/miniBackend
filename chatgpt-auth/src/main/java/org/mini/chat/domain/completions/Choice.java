package org.mini.chat.domain.completions;

import lombok.Data;

/**
 * @Description
 * @Date 2023/2/23 17:42
 * @Author Rookie
 */
@Data
public class Choice {
    private String text;
    private Integer index;
    private String logprobs;
    private String finish_reason;
}
