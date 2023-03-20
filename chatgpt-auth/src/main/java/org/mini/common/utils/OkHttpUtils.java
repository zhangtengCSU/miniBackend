package org.mini.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2023/2/18 17:21
 * @Author Rookie
 */
@Slf4j
public class OkHttpUtils {
    /**
     * max connection time
     */
    public final static int CONNECTION_TIMEOUT = 60;
    /**
     * JSON format
     */
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * OkHTTP max-available-thread-pool
     */
    public final static int MAX_IDLE_CONNECTIONS = 100;
    /**
     * OkHTTP live-time of thread-pool
     */
    public final static long KEEP_ALIVE_DURATION = 120L;

    public final static long READ_TIMEOUT = 120L;

    public final static long WRITE_TIMEOUT = 120L;

    /**
     * client
     * config for retry
     */
    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
            .build();


    /**
     * get method
     *
     * @param url     url
     * @param headers request header
     * @return response
     */
    public static String get(String url, Map<String, String> headers) {
        try {
            Request.Builder builder = new Request.Builder();
            buildHeader(builder, headers);
            Request request = builder.url(url).build();
            Response response = HTTP_CLIENT.newCall(request).execute();

            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                String result = response.body().string();
                log.info("execute 【get】 url: {} success，returns: {}", url, result);
                return result;
            }
        } catch (Exception e) {
            log.error("execute 【get】 url: {} failed!", url, e);
        }
        return null;
    }


    /**
     * Form table commit
     *
     * @param url    url
     * @param params form params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if (!CollectionUtils.isEmpty(params)) {
                params.forEach(builder::add);
            }
            FormBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            //success
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


    /**
     * simple post request
     *
     * @param url     request url
     * @param headers request header
     * @param json    request params
     * @return
     */
    public static String post(String url, Map<String, String> headers, String json) {
        try {
            okhttp3.RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
            Request.Builder builder = new Request.Builder();
            buildHeader(builder, headers);
            Request request = builder.url(url).post(body).build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                String result = response.body().string();
                log.info("SUCCESS to execute 【post】 url: {} ,params: {} ，returns: {}", url, json, result);
                return result;
            } else if (response.code() == 400 || response.code() == 500 && Objects.nonNull(response.body())) {
                String result = response.body().string();
                log.error("【For debug】params: {},res:{}", json, result);
                return result;
            } else if (response.code() == 503 || response.code() == 429 && Objects.nonNull(response.body())) {
                String result = response.body().string();
                return result;
            }
        } catch (Exception e) {
            log.error("FAILED to execute 【post】 url: {}  params: {} !", url, json, e);
        }
        return null;
    }


    /**
     * set header
     *
     * @param builder .
     * @param headers header
     */
    private static void buildHeader(Request.Builder builder, Map<String, String> headers) {
        if (Objects.nonNull(headers) && headers.size() > 0) {
            headers.forEach((k, v) -> {
                if (Objects.nonNull(k) && Objects.nonNull(v)) {
                    builder.addHeader(k, v);
                }
            });
        }
    }
}
