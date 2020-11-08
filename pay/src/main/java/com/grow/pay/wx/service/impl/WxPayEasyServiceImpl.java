package com.grow.pay.wx.service.impl;

import com.grow.pay.wx.WxPayRequest;
import com.grow.pay.wx.context.*;
import com.grow.pay.wx.service.WxBuildService;
import com.grow.pay.wx.service.WxPayEasyService;
import com.grow.pay.wx.tool.WXPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:21
 */
@Service
@Slf4j
public class WxPayEasyServiceImpl implements WxPayEasyService {

    private final WXPay wxPay;

    private final WxBuildService wxBuildService;

    public WxPayEasyServiceImpl(WXPay wxPay, WxBuildService wxBuildService) {
        this.wxPay = wxPay;
        this.wxBuildService = wxBuildService;
    }

    @Override
    public Map<String, String> appPay(WxPayRequest wxPayRequest) {
        wxPay.checkWXPayConfig();
        return wxBuildService.buildPay(() -> wxPay.unifiedOrder(wxPayRequest.getParams()));
    }

    @Override
    public void orderQuery(WxPayRequest wxPayRequest, WxOrderQueryContext wxOrderQueryContext) {
        wxPay.checkWXPayConfig();
        final Map<String, String> resp = wxBuildService.buildGeneral(() -> wxPay.orderQuery(wxPayRequest.getParams()));
        wxOrderQueryContext.buildOrderQueryContext(resp);
    }

    @Override
    public void notify(HttpServletRequest request, WxNotifyContext wxNotifyContext) {
        wxBuildService.buildNotifyContext(request, params -> {
            if (wxPay.isPayResultNotifySignatureValid(params)) {
                wxNotifyContext.buildNotifyContext(params);
            }
        });
    }

    @Override
    public void refund(WxPayRequest wxPayRequest, WxRefundContext wxRefundContext) {
        final Map<String, String> data = wxBuildService.buildGeneral(() -> wxPay.refund(wxPayRequest.getParams()));
        wxRefundContext.buildRefundContext(data);
    }

    @Override
    public void refundQuery(WxPayRequest wxPayRequest, WxRefundQueryContext wxRefundQueryContext) {
        final Map<String, String> resp = wxBuildService.buildGeneral(() -> wxPay.refundQuery(wxPayRequest.getParams()));
        wxRefundQueryContext.buildRefundQueryContext(resp.get("refund_status_$n"));
    }

    @Override
    public void refundNotify(HttpServletRequest request, WxRefundNotifyContext wxRefundNotifyContext) {
        wxBuildService.buildNotifyContext(request, params -> {
            if (wxPay.isPayResultNotifySignatureValid(params)) {
                wxRefundNotifyContext.buildRefundNotifyContext();
            }
        });
    }

    @Override
    public void closeOrder(WxPayRequest wxPayRequest, WxCloseOrderContext wxCloseOrderContext) {
        final Map<String, String> data = wxBuildService.buildGeneral(() -> wxPay.closeOrder(wxPayRequest.getParams()));
        wxCloseOrderContext.buildCloseOrderContext(data);
    }

    @Override
    public void reverse(WxPayRequest wxPayRequest, WxReverseContext wxReverseContext) {
        final Map<String, String> data = wxBuildService.buildGeneral(() -> wxPay.reverse(wxPayRequest.getParams()));
        wxReverseContext.buildReverseContext(data);
    }

    @Override
    public void transfers(WxPayRequest wxPayRequest, WxTransfersContext wxTransfersContext) {
        final Map<String, String> data = wxBuildService.buildGeneral(() -> wxPay.transfers(wxPayRequest.getParams()));
        wxTransfersContext.buildTransfersContext(data);
    }


}
