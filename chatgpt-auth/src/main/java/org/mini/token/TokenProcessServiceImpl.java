package org.mini.token;

import lombok.extern.slf4j.Slf4j;
import org.mini.token.domain.TokenizerConstant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

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
        return 2000;
    }
}
