package org.mini.chat.domain.enums;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Url2KeyEnum {
    @Deprecated
    URL1("https://chatgptforwechat.azurewebsites.net/api/chatgpt_for_wechat?code=", "stdServerCode"),
    @Deprecated
    URL2("https://gpt-test.azurewebsites.net/api/chatgpt_for_wechat?code=", "testCode"),
    URL3("https://gptforwechat-1.gptpluseve.workers.dev?code=","cfCode");
    private String url;
    private String keyName;

    Url2KeyEnum(String url, String keyName) {
        this.url = url;
        this.keyName = keyName;
    }

    @Deprecated
    public static Url2KeyEnum selectUrl() {
        int seed = (int) (Math.random() * 100) + 1;
        if (seed % 2 == 0) {
            return URL1;
        } else {
            return URL2;
        }
    }
    public static Url2KeyEnum useCfCode() {
        return URL1;
    }
}
