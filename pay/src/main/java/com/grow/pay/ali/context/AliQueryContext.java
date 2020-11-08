package com.grow.pay.ali.context;

import com.grow.pay.inter.Context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 9:52
 */
public interface AliQueryContext extends Context {

    /**
     * 回调支付宝订单查询响应
     */
    void buildQueryContext(final Map<String, Object> map);
}
