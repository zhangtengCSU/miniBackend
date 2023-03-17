package org.mini.common.http;

import org.mini.common.exceptions.GptException;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Function;

/**
 * @Description
 * @Date 2022/11/3 10:09
 * @Author Rookie
 */

public abstract class BaseController {
    /**
     * dealWithException
     */
    protected <P, R> GptHttpResponse<R> dealWithException(P param, Function<P, R> func, String logPre) {
        try {
            R rst = func.apply(param);
            return GptHttpResponse.SUCCEED(rst).build();
        } catch (GptException e) {
            getLog().warn(String.format(logPre + " business exception：%s", e.getMsg()), e);
            return GptHttpResponse.<R>FAILED(e.getCode(), e.getMsg()).build();
        } catch (IllegalArgumentException e) {
            GptHttpResponse.HttpResponseState code = GptHttpResponse.HttpResponseState.PARM_ILLEGAL;
            String message = Optional.ofNullable(e.getMessage()).orElse(code.getDesc());
            getLog().warn(String.format(logPre + " param illegal：%s", message), e);
            return GptHttpResponse.<R>FAILED(code, message).build();
        } catch (Exception e) {
            getLog().warn(logPre + " unknown exception", e);
            return GptHttpResponse.<R>SYSTEM_ERROR().build();
        }
    }

    /**
     * getLog
     */
    protected abstract Logger getLog();

}