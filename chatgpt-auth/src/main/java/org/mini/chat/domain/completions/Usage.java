package org.mini.chat.domain.completions;

import lombok.Data;

/**
 * @Description
 * @Date 2023/2/23 17:40
 * @Author Rookie
 */
@Data
public class Usage {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}
