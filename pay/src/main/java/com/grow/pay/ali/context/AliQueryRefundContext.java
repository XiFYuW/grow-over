package com.grow.pay.ali.context;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 10:17
 */
public interface AliQueryRefundContext {
    /**
     * 回调支付宝退款结果查询
     */
    void buildQueryRefundContext(final Map<String, Object> map);
}
