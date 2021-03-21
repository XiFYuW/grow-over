package com.grow.common.exception;

import com.alibaba.fastjson.JSON;
import com.grow.common.enums.ResultEnum;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class BaseExceptionAdvice {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult errorHandlerThrowable(Throwable throwable) {
        log.error(ThrowableUtil.getStackTrace(throwable));
        return ResponseResultUtils.getResponseResult(ResultEnum.SYSTEM_EXCEPTION.getCode(),
                ResultEnum.SYSTEM_EXCEPTION.getMessage());
    }

    /**
     * 处理BindException异常 参数异常提示
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseResult errorHandlerThrowable(BindException bindException) {
        BindingResult bindingResult = bindException.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        AtomicReference<String> message = new AtomicReference<>("");
        Optional.ofNullable(fieldError).ifPresent(x -> message.set(x.getField() + x.getDefaultMessage()));
        return ResponseResultUtils.getResponseResult(ResultEnum.SYSTEM_PARAM_ERR.getCode(), message.get());
    }

    /**
     * 自定义异常BaseException
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseResult errorHandlerBaseException(BaseException baseException) {
        ResponseResult responseResult = baseException.getResponseResult();
        log.info("处理异常返回：【{}】", JSON.toJSONString(responseResult));
        return responseResult;
    }

    /**
     * 自定义异常BaseRuntimeException
     */
    @ExceptionHandler(value = BaseRuntimeException.class)
    public ResponseResult errorHandlerBaseRuntimeException(BaseRuntimeException baseRuntimeException) {
        ResponseResult responseResult = baseRuntimeException.getResponseResult();
        log.info("处理异常返回：【{}】", JSON.toJSONString(responseResult));
        return responseResult;
    }

    /**
     * 自定义异常BaseRuntimeException
     */
    @ExceptionHandler(value = BaseRuntimeErrHintException.class)
    public ResponseResult errorHandlerBaseRuntimeErrHintException(BaseRuntimeErrHintException baseRuntimeErrHintException) {
        String msg = baseRuntimeErrHintException.getMsg();
        log.info("处理异常返回：【{}】", msg);
        return ResponseResultUtils.getResponseResultF(msg);
    }

    /**
     * 处理账号异常
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseResult handleAccessDeniedException(AccountExpiredException e) {
        return ResponseResultUtils.getResponseResult(NETWORK_AUTHENTICATION_REQUIRED.value(), e.getMessage());
    }

    /**
     * 处理接口无权访问异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(AccessDeniedException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return ResponseResultUtils.getResponseResult(ACCEPTED.value(), e.getMessage());
    }


}
