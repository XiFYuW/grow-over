package com.grow.pay.ali.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.*;
import com.alipay.easysdk.util.generic.models.AlipayOpenApiGenericResponse;
import com.grow.pay.ali.AliPayRequest;
import com.grow.pay.exception.AliRuntimeException;
import com.grow.pay.ali.context.*;
import com.grow.pay.ali.service.AliBuildService;
import com.grow.pay.ali.service.AliPayEasyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 8:54
 */
@Service
@Slf4j
public class AliPayEasyServiceImpl implements AliPayEasyService {

    private final AliBuildService aliBuildService;

    public AliPayEasyServiceImpl(AliBuildService aliBuildService) {
        this.aliBuildService = aliBuildService;
    }

    @Override
    public Map<String, Object> appPay(AliPayRequest aliPayRequest) {
        return aliBuildService.buildPay(() -> Factory
                .Payment
                .App()
                .asyncNotify(aliPayRequest.getAsyncNotify())
                .pay(
                        aliPayRequest.getSubject(),
                        aliPayRequest.getOutTradeNo(),
                        aliPayRequest.getTotalAmount()
                ));
    }

    @Override
    public void notify(HttpServletRequest request, AliNotifyContext aliNotifyContext) {
        aliBuildService.buildNotifyContext(request, params -> {
            boolean isVerify = Factory.Payment.Common().verifyNotify(params);
            log.info("支付宝验签结果：【{}】", isVerify);
            if (isVerify) {
                aliNotifyContext.buildNotifyContext(params);
            }
        });

    }

    @Override
    public void refund(AliPayRequest aliPayRequest, AliRefundContext aliRefundContext) {
        aliBuildService.buildGeneral(() -> {
            final AlipayTradeRefundResponse alipayTradeRefundResponse = Factory
                    .Payment
                    .Common()
                    .refund(
                            aliPayRequest.getOutTradeNo(),
                            aliPayRequest.getRefundAmount()
                    );
            log.info("支付宝退款结果：【{}】", JSON.toJSONString(alipayTradeRefundResponse));
            if (!"10000".equals(alipayTradeRefundResponse.code)) {
                throw new AliRuntimeException(alipayTradeRefundResponse.subMsg);
            }
            aliRefundContext.buildRefundContext(alipayTradeRefundResponse.toMap());
        });
    }

    @Override
    public void query(AliPayRequest aliPayRequest, AliQueryContext aliQueryContext) {
        aliBuildService.buildGeneral(() -> {
            final AlipayTradeQueryResponse alipayTradeQueryResponse = Factory
                    .Payment
                    .Common()
                    .query(aliPayRequest.getOutTradeNo());
            log.info("支付宝订单查询结果：【{}】", JSON.toJSONString(alipayTradeQueryResponse));
            if (!"10000".equals(alipayTradeQueryResponse.code)) {
                throw new AliRuntimeException(alipayTradeQueryResponse.subMsg);
            }
            aliQueryContext.buildQueryContext(alipayTradeQueryResponse.tradeStatus);
        });
    }

    @Override
    public void queryRefund(AliPayRequest aliPayRequest, AliQueryRefundContext aliQueryRefundContext) {
        aliBuildService.buildGeneral(() -> {
            final AlipayTradeFastpayRefundQueryResponse alipayTradeFastpayRefundQueryResponse = Factory
                    .Payment
                    .Common()
                    .queryRefund(
                            aliPayRequest.getOutTradeNo(),
                            aliPayRequest.getRequestNo()
                    );
            log.info("支付宝退款订单查询结果：【{}】", JSON.toJSONString(alipayTradeFastpayRefundQueryResponse));
            if (!"10000".equals(alipayTradeFastpayRefundQueryResponse.code)) {
                throw new AliRuntimeException(alipayTradeFastpayRefundQueryResponse.subMsg);
            }
            aliQueryRefundContext.buildQueryRefundContext(alipayTradeFastpayRefundQueryResponse.toMap());
        });
    }

    @Override
    public void cancel(AliPayRequest aliPayRequest, AliCancelContext aliCancelContext) {
        aliBuildService.buildGeneral(() -> {
            final AlipayTradeCancelResponse alipayTradeCancelResponse = Factory
                    .Payment
                    .Common()
                    .cancel(aliPayRequest.getOutTradeNo());
            log.info("支付宝撤销交易结果：【{}】", JSON.toJSONString(alipayTradeCancelResponse));
            if (!"10000".equals(alipayTradeCancelResponse.code)) {
                throw new AliRuntimeException(alipayTradeCancelResponse.subMsg);
            }
            aliCancelContext.buildCancelContext(alipayTradeCancelResponse.toMap());
        });
    }

    @Override
    public void close(AliPayRequest aliPayRequest, AliCloseContext aliCloseContext) {
        aliBuildService.buildGeneral(() -> {
            final AlipayTradeCloseResponse alipayTradeCloseResponse = Factory
                    .Payment
                    .Common()
                    .close(aliPayRequest.getOutTradeNo());
            log.info("支付宝关闭交易结果：【{}】", JSON.toJSONString(alipayTradeCloseResponse));
            if (!"10000".equals(alipayTradeCloseResponse.code)) {
                throw new AliRuntimeException(alipayTradeCloseResponse.subMsg);
            }
            aliCloseContext.buildCloseContext(alipayTradeCloseResponse.toMap());
        });
    }

    @Override
    public void GenericExecute(AliPayRequest aliPayRequest, AliGenericExecuteContext aliGenericExecuteContext) {
        aliBuildService.buildGenericExecute(() -> {
            final AlipayOpenApiGenericResponse alipayOpenApiGenericResponse = Factory
                    .Util
                    .Generic()
                    .execute(
                            aliPayRequest.getMethod(),
                            aliPayRequest.getTextParams(),
                            aliPayRequest.getBizParams()
                    );
            log.info("支付宝调用【{}】返回结果：【{}】", aliPayRequest.getMethod(), JSON.toJSONString(alipayOpenApiGenericResponse));
            if (!"10000".equals(alipayOpenApiGenericResponse.code)) {
                throw new AliRuntimeException(alipayOpenApiGenericResponse.subMsg);
            }
            aliGenericExecuteContext.buildGenericExecuteContext(alipayOpenApiGenericResponse.toMap());
        });
    }
}
