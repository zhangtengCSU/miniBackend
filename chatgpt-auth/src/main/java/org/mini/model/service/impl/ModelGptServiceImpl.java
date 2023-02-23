package org.mini.model.service.impl;

import org.mini.model.service.ModelGptService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2023/2/23 14:54
 * @Author Rookie
 */
@Service
public class ModelGptServiceImpl implements ModelGptService {
    @Override
    public String matchModelType() {
        return "completions";
    }
}
