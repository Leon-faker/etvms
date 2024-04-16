package com.lihang.etvms.adapter.controller.response;


import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;

/**
 * HTTP 响应
 *
 * @date 2023/5/17
 **/
public class Response<T> {

    private static final String SUCCESS_CODE = "0";

    /**
     * 返回码，为0时没有错误
     */
    private String code;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 返回值
     */
    private T data;

    public static <T> Response<T> error(EtvmsSystemException exception) {
        Response<T> response = new Response<>();
        response.setCode(exception.getCode());
        response.setErrorMessage(exception.getZhMessage());
        response.setData(null);
        return response;
    }

    public static <T> Response<T> error(EtvmsSystemExceptionMessage exceptionMessage) {
        Response<T> response = new Response<>();
        response.setCode(exceptionMessage.getCode());
        response.setErrorMessage(exceptionMessage.getZhMessage());
        response.setData(null);
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(SUCCESS_CODE);
        response.setErrorMessage(null);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        response.setCode(SUCCESS_CODE);
        response.setErrorMessage(null);
        response.setData(null);
        return response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
