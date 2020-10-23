package com.grow.common.enums;

public enum ResultEnum {
    TOKEN_EXPIRED("token失效，请重新登录", 50014),

    TOKEN_ERR("token错误", 50013),

    FAILED("操作失败", 5000),

    SUCCESS("操作成功", 2000),

    SYSTEM_PARAM_ERR("参数错误，请检查参数", 504),

    SYSTEM_EXCEPTION("系统异常，请联系管理员", 500);

    String message;

    int code;

    ResultEnum(String msg, int code) {
        this.message = msg;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
