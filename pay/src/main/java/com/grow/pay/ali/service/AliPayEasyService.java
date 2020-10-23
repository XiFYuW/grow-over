package com.grow.pay.ali.service;

import com.grow.pay.inter.PayEasy;
import com.grow.pay.ali.AliPayRequest;
import com.grow.pay.ali.context.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 8:54
 */
public interface AliPayEasyService extends PayEasy {

    Map<String, Object> appPay(AliPayRequest aliPayRequest);

    void notify(HttpServletRequest request, AliNotifyContext aliNotifyContext);

    void refund(AliPayRequest aliPayRequest, AliRefundContext aliRefundContext);

    void query(AliPayRequest aliPayRequest, AliQueryContext aliQueryContext);

    void queryRefund(AliPayRequest aliPayRequest, AliQueryRefundContext aliQueryRefundContext);

    void cancel(AliPayRequest aliPayRequest, AliCancelContext aliCancelContext);

    void close(AliPayRequest aliPayRequest, AliCloseContext aliCloseContext);

    void GenericExecute(AliPayRequest aliPayRequest, AliGenericExecuteContext aliGenericExecuteContext);
}
