package com.grow.common.id;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GoodsGenContext {

    private static final String PREFIX = "SBD-SP";

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static final AtomicInteger SEQ = new AtomicInteger(1000);

    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddSS");

    public static synchronized String generateGoodsNo(){
        final LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue() > 19999){
            SEQ.getAndSet(1000);
        }
        // SBD-SP 200925155 10002 14
        return PREFIX + dataTime.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }


    public static void main(String[] args) {
        final List<String> goodsNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, 10000).parallel().forEach(i-> goodsNos.add(generateGoodsNo()));

        final List<String> filterOrderNos = goodsNos.stream().distinct().collect(Collectors.toList());

        System.out.println("订单样例：" + goodsNos.get(22));
        System.out.println("生成订单数：" + goodsNos.size());
        System.out.println("过滤重复后订单数：" + filterOrderNos.size());
        System.out.println("重复订单数：" +(goodsNos.size() - filterOrderNos.size()));
    }
}
