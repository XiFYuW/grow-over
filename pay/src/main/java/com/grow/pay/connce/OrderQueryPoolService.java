package com.grow.pay.connce;

import cn.hutool.core.date.DateUtil;
import com.grow.pay.ali.service.AliPayEasyService;
import com.grow.pay.connce.store.ConsequenceMapper;
import com.grow.pay.connce.store.ConsequencePay;
import com.grow.pay.wx.service.WxPayEasyService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 16:31
 */
@Slf4j
public class OrderQueryPoolService implements OrderQueryPoolExecutorService {

    private final ThreadPoolExecutor orderQueryExecutor;

    private final ScheduledExecutorService cleanOrderQueryExecutors;

    private final ScheduledExecutorService orderQueryLaterExecutors;

    private final ConsequenceMapper consequenceMapper;

    public OrderQueryPoolService(ConsequenceMapper consequenceMapper) {
        this.consequenceMapper = consequenceMapper;
        BlockingQueue<Runnable> orderQueryQueue = new LinkedBlockingQueue<>();
        this.orderQueryExecutor = new ThreadPoolExecutor(
                5,
                5,
                1000 * 60,
                TimeUnit.MILLISECONDS,
                orderQueryQueue,
                new OrderQueryThreadFactoryImpl("OrderQueryExecutorThread_"));
        this.cleanOrderQueryExecutors = Executors.newSingleThreadScheduledExecutor(new OrderQueryThreadFactoryImpl("CleanOrderQueryExecutorsScheduledThread_"));
        this.orderQueryLaterExecutors = Executors.newSingleThreadScheduledExecutor(new OrderQueryThreadFactoryImpl("OrderQueryLaterExecutorsScheduledThread_"));
    }

    @Override
    public void start() {
        /*清除订单查询任务*/
        this.cleanOrderQueryExecutors.scheduleAtFixedRate(() -> {
            /*扫表 >> 重入*/
            List<ConsequencePay> consequencePayList = consequenceMapper.selectByIsPerform();
            if (consequencePayList != null && consequencePayList.size() > 0) {
                log.warn("扫表数量: {}", consequencePayList.size());
                consequencePayList.forEach(x -> {
                    final Consequence consequence = new Consequence(
                            x.getIsConsequence() == 1,
                            x.getOrderNo(),
                            x.getPayWay() == 1 ? ConsequenceQueryEnum.ALI_QUERY.getPayWay() : ConsequenceQueryEnum.WX_QUERY.getPayWay(),
                            x.getPayWay() == 1 ? SpringContextUtil.getBean(AliPayEasyService.class) : SpringContextUtil.getBean(WxPayEasyService.class)
                    );
                    this.submitRequestNonTable(consequence);
                });
            }
        }, 1, 30 * 60, TimeUnit.SECONDS);
    }

    @Override
    public void shutdown() {
        orderQueryExecutor.shutdown();
        cleanOrderQueryExecutors.shutdown();
        orderQueryLaterExecutors.shutdown();
    }

    @Override
    public void submitRequest(final Consequence consequence) {
        if (consequenceMapper.selectByOrderNo(consequence.getOutTradeNo()) == null/*去重*/) {
            consequenceMapper.insertSelective(ConsequencePay
                    .builder()
                    .createTime(DateUtil.date())
                    .isConsequence(consequence.isConsequence() ? 1 : 0)
                    .isPerform(0)
                    .payWay(consequence.getPayWay())
                    .orderNo(consequence.getOutTradeNo())
                    .build()
            );

            final ConsequenceRequest consequenceRequest = new ConsequenceRequest(consequence);
            submit(consequenceRequest);
        }
    }

    @Override
    public void submitRequestNonTable(Consequence consequence) {
        final ConsequenceRequest consequenceRequest = new ConsequenceRequest(consequence);
        submit(consequenceRequest);
    }

    private void submit(final ConsequenceRequest consequenceRequest){
        try {
            this.orderQueryExecutor.submit(consequenceRequest/*入池*/);
            log.warn("订单：{}, 入orderQueryExecutor成功", consequenceRequest.getConsequence().getOutTradeNo());
        }catch (RejectedExecutionException e){
            submitLater(consequenceRequest/*失败 >> 入池*/);
            log.warn("订单：{}, 入orderQueryLaterExecutors成功", consequenceRequest.getConsequence().getOutTradeNo());
        }
    }

    private void submitLater(final ConsequenceRequest consequenceRequest) {
        this.orderQueryLaterExecutors.schedule((Runnable) () -> OrderQueryPoolService.this.orderQueryExecutor.submit(consequenceRequest), 5000, TimeUnit.MILLISECONDS);
    }
}
