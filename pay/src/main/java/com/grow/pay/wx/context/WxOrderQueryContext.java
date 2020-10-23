package com.grow.pay.wx.context;

import com.grow.pay.inter.Context;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:33
 */
public interface WxOrderQueryContext extends Context {

    /**
     * 回调微信订单查询响应
     * @param tradeState 支付状态，参考TradeStateEnum
     */
    void buildOrderQueryContext(String tradeState);
}
