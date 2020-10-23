package com.grow.pay.connce;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 16:31
 */
@Slf4j
public class OrderQueryPoolService implements OrderQueryPoolExecutorService {

    private final BlockingQueue<Runnable> orderQueryQueue;

    private final ThreadPoolExecutor orderQueryExecutor;

    private final ScheduledExecutorService cleanOrderQueryExecutors;

    private final ScheduledExecutorService orderQueryLaterExecutors;

    public OrderQueryPoolService() {
        this.orderQueryQueue = new LinkedBlockingQueue<>();
        this.orderQueryExecutor = new ThreadPoolExecutor(
                10,
                20,
                1000 * 60,
                TimeUnit.MILLISECONDS,
                this.orderQueryQueue,
                new OrderQueryThreadFactoryImpl("OrderQueryExecutorThread_"));
        this.cleanOrderQueryExecutors = Executors.newSingleThreadScheduledExecutor(new OrderQueryThreadFactoryImpl("CleanOrderQueryExecutorsScheduledThread_",true));
        this.orderQueryLaterExecutors = Executors.newSingleThreadScheduledExecutor(new OrderQueryThreadFactoryImpl("OrderQueryLaterExecutorsScheduledThread_"));
    }

    @Override
    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        this.cleanOrderQueryExecutors.scheduleAtFixedRate(() -> {
            /*清除订单查询任务*/
            log.info("清除订单查询任务");
        }, 1000, 10000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void shutdown() {
        orderQueryExecutor.shutdown();
        cleanOrderQueryExecutors.shutdown();
        orderQueryLaterExecutors.shutdown();
    }

    @Override
    public void submitRequest(final Consequence consequence) {
        final ConsequenceRequest consequenceRequest = new ConsequenceRequest(consequence);
        try {
            this.orderQueryExecutor.submit(consequenceRequest);
        }catch (RejectedExecutionException e){
            submitRequestLater(consequenceRequest);
        }

    }

    private void submitRequestLater(final ConsequenceRequest consequenceRequest) {
        this.orderQueryLaterExecutors.schedule((Runnable) () -> OrderQueryPoolService.this.orderQueryExecutor.submit(consequenceRequest), 1000, TimeUnit.MILLISECONDS);
    }
}
