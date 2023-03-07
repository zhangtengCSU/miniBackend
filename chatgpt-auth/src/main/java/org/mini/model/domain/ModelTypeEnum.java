package org.mini.model.domain;

import lombok.Getter;

@Getter
public enum ModelTypeEnum {

    CHAT("chat/completions","gpt-3.5-turbo"),
    COMPLETIONS("completions","text-davinci-003"),
    EDITS("edits","text-davinci-edit-001"),
    EMBEDDINGS("embeddings","text-embedding-ada-002"),

    ;

    private String type;
    private String model;

    ModelTypeEnum(String type, String model) {
        this.model = model;
        this.type = type;
    }
}
