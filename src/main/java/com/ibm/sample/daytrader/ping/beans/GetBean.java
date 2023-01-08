package com.ibm.sample.daytrader.ping.beans;

import com.ibm.sample.daytrader.ping.types.ResponseType;
import org.springframework.stereotype.Component;

@Component
public class GetBean {
    public ResponseType sayHello(String message) {
        return new ResponseType(message);
    }
}
