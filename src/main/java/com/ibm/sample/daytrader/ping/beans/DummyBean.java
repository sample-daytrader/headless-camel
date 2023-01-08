package com.ibm.sample.daytrader.ping.beans;

import com.ibm.sample.daytrader.ping.entities.DummyEntity;
import com.ibm.sample.daytrader.ping.utils.Log;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
public class DummyBean {
    @Handler
    public void printThis(Exchange exchange) {
        DummyEntity dummyEntity = exchange.getMessage().getBody(DummyEntity.class);
        System.out.println(dummyEntity.getMsg());
    }
}
