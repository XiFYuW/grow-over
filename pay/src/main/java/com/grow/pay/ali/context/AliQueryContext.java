package com.grow.pay.ali.context;

import com.grow.pay.inter.Context;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 9:52
 */
public interface AliQueryContext extends Context {

    /**
     * 回调支付宝订单查询响应
     * @param tradeStatus 支付状态，参考TradeStatusEnum
     */
    void buildQueryContext(final String tradeStatus);
}
