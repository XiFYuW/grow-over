package com.grow.pay.connce;

import com.grow.pay.ali.AliPayRequest;
import com.grow.pay.ali.context.AliQueryContext;
import com.grow.pay.ali.service.AliPayEasyService;
import com.grow.pay.exception.ConsequenceException;
import com.grow.pay.inter.PayEasy;
import com.grow.pay.wx.WxPayRequest;
import com.grow.pay.wx.context.WxOrderQueryContext;
import com.grow.pay.wx.service.WxPayEasyService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 13:51
 */
public final class Consequence implements Serializable {

    /*是否开启支付结果查询*/
    private final boolean isConsequence;

    /*商户订单号*/
    private final String outTradeNo;

    /*支付方式1：支付宝 2：微信*/
    private final Integer payWay;

    private final PayEasy payEasy;

    private AtomicBoolean checkPayWay = new AtomicBoolean(true);

    public Consequence(boolean isConsequence, String outTradeNo, Integer payWay, PayEasy payEasy) {
        this.isConsequence = isConsequence;
        this.outTradeNo = outTradeNo;
        this.payWay = payWay;
        this.payEasy = payEasy;
        checkPayWay();
    }

    public boolean isConsequence() {
        return isConsequence;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setCheckPayWay(AtomicBoolean checkPayWay) {
        this.checkPayWay = checkPayWay;
    }

    private void checkPayWay(){
        final ConsequenceQueryEnum[] consequenceQueryEnum = ConsequenceQueryEnum.values();
        for (ConsequenceQueryEnum c : consequenceQueryEnum) {
            checkPayWay.set(checkPayWay.get() || c.getPayWay() == payWay);
        }
    }

    public void ConsequenceQuery(){
        if (!checkPayWay.get()) {
            throw new ConsequenceException("payWay err");
        }

        if (payWay.compareTo(ConsequenceQueryEnum.ALI_QUERY.getPayWay()) == 0 && isConsequence) {
            aliConsequenceQuery((AliPayEasyService) this.payEasy);
        }

        if (payWay.compareTo(ConsequenceQueryEnum.WX_QUERY.getPayWay()) == 0 && isConsequence) {
            wxConsequenceQuery((WxPayEasyService) this.payEasy);
        }
    }

    private void aliConsequenceQuery(final AliPayEasyService aliPayEasyService){
        final AliPayRequest aliPayRequest = AliPayRequest.builder().outTradeNo(outTradeNo).build();
        aliPayEasyService.query(aliPayRequest, (AliQueryContext) ConsequenceQueryEnum.ALI_QUERY.getContext());
    }

    private void wxConsequenceQuery(final WxPayEasyService wxPayEasyService){
        final Map<String, String> params = new HashMap<>();
        final WxPayRequest wxPayRequest = WxPayRequest.builder().params(params).build();
        wxPayEasyService.orderQuery(wxPayRequest, (WxOrderQueryContext) ConsequenceQueryEnum.WX_QUERY.getContext());
    }
}
