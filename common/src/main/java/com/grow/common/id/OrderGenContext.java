package com.grow.common.id;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderGenContext {

    private static final String PREFIX = "SBD";

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static final AtomicInteger SEQ = new AtomicInteger(10000);

    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");

    private static final AtomicReference<String> IP_SUFFIX = new AtomicReference<>("");

    public static synchronized String generateOrderNo(){
        final LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue() > 19990){
            SEQ.getAndSet(10000);
        }
        // SBD 200925155 9162148 10002
        return PREFIX + dataTime.format(DF_FMT_PREFIX) + getLocalIpSuffix() + SEQ.getAndIncrement();
    }

    private static String getLocalIpSuffix(){
        synchronized (PREFIX) {
            try {
                if(!"".equals(IP_SUFFIX.get())){
                    return IP_SUFFIX.get();
                }
                final InetAddress addr = InetAddress.getLocalHost();
                // 172.17.0.4  172.17.0.199 ,
                final String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    final AtomicReference<String> ipSuffix = new AtomicReference<>(hostAddress.trim().split("\\.")[3]);
                    if (ipSuffix.get().length() == 2) {
                        IP_SUFFIX.set(ipSuffix.get());
                        return IP_SUFFIX.get();
                    }
                    ipSuffix.set("0" + ipSuffix.get());
                    IP_SUFFIX.set(String.valueOf(IdUtil.createSnowflake(1, 1).getDataCenterId(Long.valueOf(ipSuffix.get().substring(ipSuffix.get().length() - 1)))));
                    return IP_SUFFIX.get();
                }
                IP_SUFFIX.set(String.valueOf(IdUtil.createSnowflake(1, 1).getDataCenterId(RandomUtils.nextInt(10, 20))));
                return IP_SUFFIX.get();

            }catch (Exception e){
                IP_SUFFIX.set(String.valueOf(IdUtil.createSnowflake(1, 1).getDataCenterId(RandomUtils.nextInt(10, 20))));
                return IP_SUFFIX.get();
            }
        }

    }


    public static void main(String[] args) {
        final List<String> orderNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, 10000).parallel().forEach(i-> orderNos.add(generateOrderNo()));

        final List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("订单样例：" + orderNos.get(22));
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤重复后订单数：" + filterOrderNos.size());
        System.out.println("重复订单数：" +(orderNos.size() - filterOrderNos.size()));
    }
}
