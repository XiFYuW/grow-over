package com.grow.pay.ali;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 10:26
 */
public enum TradeStatusEnum {
    WAIT_BUYER_PAY("交易创建，等待买家付款","WAIT_BUYER_PAY"),
    TRADE_CLOSED("未付款交易超时关闭，或支付完成后全额退款","TRADE_CLOSED"),
    TRADE_SUCCESS("交易支付成功","TRADE_SUCCESS"),
    TRADE_FINISHED("交易结束，不可退款","TRADE_FINISHED");

    private String msg;

    private String code;

    TradeStatusEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
