package org.mini.deprecated.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2023/2/25 0:37
 * @Author Rookie
 */
@Service
@Slf4j
public class TokenProcessServiceImpl implements TokenProcessService {

    public static final Integer MAX_PROMPT = 4000;


    @Override
    public Integer countToken(String prompt) {
//        Gpt2Tokenizer tokenizer = Gpt2Tokenizer.fromPretrained("token/");
//        List<Integer> result = tokenizer.encode(prompt);
//        return MAX_PROMPT - result.size();
        return 20;
    }
}
