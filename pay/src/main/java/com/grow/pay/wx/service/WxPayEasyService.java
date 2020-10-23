package com.grow.pay.wx.service;

import com.grow.pay.inter.PayEasy;
import com.grow.pay.wx.WxPayRequest;
import com.grow.pay.wx.context.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:20
 */
public interface WxPayEasyService extends PayEasy {

    Map<String, String> appPay(WxPayRequest wxPayRequest);

    void orderQuery(WxPayRequest wxPayRequest, WxOrderQueryContext wxOrderQueryContext);

    void notify(HttpServletRequest request, WxNotifyContext wxNotifyContext);

    void refund(WxPayRequest wxPayRequest, WxRefundContext wxRefundContext);

    void refundQuery(WxPayRequest wxPayRequest, WxRefundQueryContext wxRefundQueryContext);

    void refundNotify(HttpServletRequest request, WxRefundNotifyContext wxRefundNotifyContext);

    void closeOrder(WxPayRequest wxPayRequest, WxCloseOrderContext wxCloseOrderContext);

    void reverse(WxPayRequest wxPayRequest, WxReverseContext wxReverseContext);

    void transfers(WxPayRequest wxPayRequest, WxTransfersContext wxTransfersContext);
}
