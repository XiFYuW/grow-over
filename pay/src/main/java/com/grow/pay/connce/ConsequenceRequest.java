package com.grow.pay.connce;

import lombok.extern.slf4j.Slf4j;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/20 17:31
 */
@Slf4j
public class ConsequenceRequest implements Runnable {

    private final Consequence consequence;

    public ConsequenceRequest(Consequence consequence) {
        this.consequence = consequence;
    }

    public Consequence getConsequence() {
        return consequence;
    }

    @Override
    public void run() {

        log.info("{} start query", this.consequence.getOutTradeNo());
        consequence.ConsequenceQuery();
    }
}
