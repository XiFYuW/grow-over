package com.grow.common.exception;

import com.grow.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRuntimeException extends RuntimeException{

    private ResponseResult responseResult;

    public BaseRuntimeException(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }
}
