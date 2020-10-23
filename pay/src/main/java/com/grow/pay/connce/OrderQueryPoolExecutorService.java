package com.grow.pay.connce;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 16:33
 */
public interface OrderQueryPoolExecutorService {
    void start();

    void shutdown();

    void submitRequest(final Consequence consequence);
}
