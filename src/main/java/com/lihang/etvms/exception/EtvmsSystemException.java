package com.lihang.etvms.exception;

import org.springframework.util.Assert;

/**
 * Etvms 系统自定异常
 *
 * @date 2022/12/5
 **/
public class EtvmsSystemException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 中文信息
     */
    private final String zhMessage;

    EtvmsSystemException(EtvmsSystemExceptionMessage exceptionMessage) {
        Assert.notNull(exceptionMessage, "Exception message must not be null");
        this.code = exceptionMessage.getCode();
        this.message = exceptionMessage.getMessage();
        this.zhMessage = exceptionMessage.getZhMessage();
    }

    public static EtvmsSystemException of(EtvmsSystemExceptionMessage exceptionMessage) {
        return new EtvmsSystemException(exceptionMessage);
    }

    public String getZhMessage() {
        return zhMessage;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
