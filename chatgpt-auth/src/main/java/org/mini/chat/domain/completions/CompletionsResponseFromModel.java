package org.mini.chat.domain.completions;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2023/2/23 17:37
 * @Author Rookie
 */
@Data
public class CompletionsResponseFromModel {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}
