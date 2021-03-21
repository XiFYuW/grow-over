package com.grow.common.exception;

public class BaseRuntimeErrHintException extends RuntimeException{

    private String msg;

    public BaseRuntimeErrHintException(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
