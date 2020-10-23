package com.grow.common.exception;

import com.grow.common.result.ResponseResult;

public class BaseException extends Exception{

    private ResponseResult responseResult;

    public BaseException(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }
}
