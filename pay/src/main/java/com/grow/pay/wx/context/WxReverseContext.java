package com.grow.pay.wx.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:33
 */
public interface WxReverseContext {
    /**
     * 回调微信订单撤销
     */
    void buildReverseContext(final Map<String, String> map);
}
