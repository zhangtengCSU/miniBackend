package org.mini.model.service;

/**
 * @author zt
 */
public interface ModelGptService {
    /**
     * match model type
     * completions, edit, image...
     * @return uri
     */
    String matchModelType();
}
