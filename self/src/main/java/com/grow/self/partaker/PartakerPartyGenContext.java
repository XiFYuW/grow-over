package com.grow.self.partaker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

class PartakerPartyGenContext {

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static final AtomicInteger SEQ = new AtomicInteger(1000);

    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddSS");

    static synchronized String generatePartakerPartyNo(final String prefix){
        final LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue() > 19999){
            SEQ.getAndSet(1000);
        }
        // SBD-SP 200925155 10002 14
        return prefix + dataTime.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }
}
