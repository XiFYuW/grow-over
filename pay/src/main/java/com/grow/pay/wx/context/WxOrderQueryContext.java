package com.grow.pay.wx.context;

import com.grow.pay.inter.Context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:33
 */
public interface WxOrderQueryContext extends Context {

    /**
     * 回调微信订单查询响应
     */
    void buildOrderQueryContext(Map<String, String> resp);
}
