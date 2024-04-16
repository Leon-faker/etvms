package com.lihang.etvms.exception;

import com.lihang.etvms.adapter.controller.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * 全局异常处理器
 *
 * @date 2022/12/9
 **/
@RestControllerAdvice
public class EtvmsSystemGlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(value = EtvmsSystemException.class)
    public Response<String> hftBackOfficeSystemExceptionHandler(EtvmsSystemException exception) {
        LOGGER.error("[HFT Back Office System Exception]", exception);
        return Response.error(exception);
    }

    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(Exception exception) {
        LOGGER.error("[Unknown Exception]", exception);
        EtvmsSystemExceptionMessage unknownException = EtvmsSystemExceptionMessage.UNKNOWN_EXCEPTION;
        return Response.error(unknownException);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Response<String> NPEHandler(NullPointerException exception) {
        LOGGER.error("[Null Pointer Exception]", exception);
        EtvmsSystemExceptionMessage nullPointerException = EtvmsSystemExceptionMessage.NULL_POINTER_EXCEPTION;
        return Response.error(nullPointerException);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        LOGGER.error("[MethodArgumentNotValidException Exception]", exception);
        EtvmsSystemExceptionMessage exceptionMessage = EtvmsSystemExceptionMessage.REQUEST_PARAM_VALIDATE;
        BindingResult exceptions = exception.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                exceptionMessage.setZhMessage(fieldError.getDefaultMessage());
                return Response.error(exceptionMessage);
            }
        }
        return Response.error(exceptionMessage);
    }
}
