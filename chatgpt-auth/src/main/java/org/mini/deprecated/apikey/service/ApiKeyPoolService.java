package org.mini.deprecated.apikey.service;

/**
 * @author zt
 */
public interface ApiKeyPoolService {
    /**
     * select a key from keyPool for send request
     * @return apiKey
     */
    String selectKey();
}
