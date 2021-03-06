package com.grow.pay.wx.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 17:33
 */
public interface WxCloseOrderContext {

    /**
     * 回调微信订单关闭
     */
    void buildCloseOrderContext(final Map<String, String> data);
}
