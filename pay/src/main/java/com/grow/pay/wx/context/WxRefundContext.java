package com.grow.pay.wx.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:33
 */
public interface WxRefundContext {
    /**
     * 回调微信订单退款
     */
    void buildRefundContext(final Map<String, String> data);
}
