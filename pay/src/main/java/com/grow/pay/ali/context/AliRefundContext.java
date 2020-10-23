package com.grow.pay.ali.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 10:17
 */
public interface AliRefundContext {
    /**
     * 回调支付宝退款
     */
    void buildRefundContext(final Map<String, Object> map);
}
