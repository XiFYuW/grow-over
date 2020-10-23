package com.grow.pay.connce;

import com.grow.pay.ali.TradeStatusEnum;
import com.grow.pay.ali.context.AliQueryContext;
import com.grow.pay.inter.Context;
import com.grow.pay.wx.TradeStateEnum;
import com.grow.pay.wx.context.WxOrderQueryContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 14:37
 */
@Slf4j
public enum ConsequenceQueryEnum {

    ALI_QUERY(1, (AliQueryContext) tradeStatus -> {
        TradeStatusEnum tradeStatusEnum = TradeStatusEnum.valueOf(tradeStatus);
        log.info(tradeStatusEnum.getMsg());
    }),

    WX_QUERY(2, (WxOrderQueryContext) tradeState -> {
        TradeStateEnum tradeStateEnum = TradeStateEnum.valueOf(tradeState);
        log.info(tradeStateEnum.getMsg());
    })
    ;

    private int payWay;

    private Context context;


    ConsequenceQueryEnum(int payWay, Context context) {
        this.payWay = payWay;
        this.context = context;
    }

    public int getPayWay() {
        return payWay;
    }

    public Context getContext() {
        return context;
    }

}
