package com.grow.pay.connce;

import com.grow.pay.ali.TradeStatusEnum;
import com.grow.pay.ali.context.AliQueryContext;
import com.grow.pay.connce.store.ConsequenceMapper;
import com.grow.pay.connce.store.ConsequencePay;
import com.grow.pay.inter.Context;
import com.grow.pay.wx.TradeStateEnum;
import com.grow.pay.wx.context.WxOrderQueryContext;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 14:37
 */
@Slf4j
public enum ConsequenceQueryEnum {

    ALI_QUERY(1, (AliQueryContext) map -> {
        String tradeStatus = String.valueOf(map.get("trade_status"));
        String outTradeNo = String.valueOf(map.get("out_trade_no"));
        TradeStatusEnum tradeStatusEnum = TradeStatusEnum.valueOf(tradeStatus);

        ConsequenceMapper consequenceMapper = SpringContextUtil.getBean(ConsequenceMapper.class);
        ConsequencePay consequencePay = consequenceMapper.selectByOrderNo(outTradeNo);

        if (consequencePay.getIsPerform() == 0) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("orderNo", outTradeNo);
            map1.put("aliOrderStatus", tradeStatusEnum.getCode());
            consequenceMapper.updateStatus(map1);
        }
    }),

    WX_QUERY(2, (WxOrderQueryContext) resp -> {
        String tradeState = resp.get("trade_state");
        String outTradeNo = resp.get("out_trade_no");
        TradeStateEnum tradeStateEnum = TradeStateEnum.valueOf(tradeState);

        ConsequenceMapper consequenceMapper = SpringContextUtil.getBean(ConsequenceMapper.class);
        ConsequencePay consequencePay = consequenceMapper.selectByOrderNo(outTradeNo);

        if (consequencePay.getIsPerform() == 0) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("orderNo", outTradeNo);
            map1.put("wxOrderStatus", tradeStateEnum.getCode());
            consequenceMapper.updateStatus(map1);
        }
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
