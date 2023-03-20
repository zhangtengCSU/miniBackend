package org.mini.chat.domain.enums;

import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Date 2023/3/20 13:29
 * @Author Rookie
 */
public class MicrosoftResponseCode {
    public static final String CODE_503 = "503";
    public static final String CODE_429 = "429";
    public static final String CODE_400 = "400";
    public static final String CODE_500 = "500";
    public static final String CODE_200 = "200";

    public static final Set<String> THROWABLE_ERROR = Sets.newHashSet(CODE_429,CODE_503);
    public static final Set<String> NOT_THROWABLE_ERROR = Sets.newHashSet(CODE_400,CODE_500);
}
