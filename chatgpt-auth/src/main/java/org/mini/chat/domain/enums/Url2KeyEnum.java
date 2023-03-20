package org.mini.chat.domain.enums;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Url2KeyEnum {
    URL1("https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat?code=", "stdServerCode"),
    URL2("https://gpt-test.azurewebsites.net/api/chatgpt_for_wechat?code=", "testCode");

    private String url;
    private String keyName;

    Url2KeyEnum(String url, String keyName) {
        this.url = url;
        this.keyName = keyName;
    }

    public static Url2KeyEnum selectUrl() {
        int seed = (int) (Math.random() * 100) + 1;
        if (seed % 2 == 0) {
            return URL1;
        } else {
            return URL2;
        }
    }
}
