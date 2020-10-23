package com.grow.pay.wx;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:45
 */
public enum TradeStateEnum {
    SUCCESS("支付成功","SUCCESS"),
    REFUND("转入退款","REFUND"),
    NOTPAY("未支付","NOTPAY"),
    REVOKED("已撤销（付款码支付）","REVOKED"),
    USERPAYING("用户支付中（付款码支付）","USERPAYING"),
    PAYERROR("支付失败(其他原因，如银行返回失败)","NOTPAY"),
    CLOSED("已关闭","CLOSED");

    private String msg;

    private String code;

    TradeStateEnum(String msg, String code) {
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
