package com.grow.pay.ali.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 9:52
 */
public interface AliCloseContext {
    /**
     * 回调支付宝订单关闭
     */
    void buildCloseContext(final Map<String, Object> map);
}
