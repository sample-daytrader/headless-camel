package com.ibm.sample.daytrader.ping.beans;

import com.ibm.sample.daytrader.ping.types.RequestType;
import com.ibm.sample.daytrader.ping.types.ResponseType;
import org.springframework.stereotype.Component;

@Component
public class PostBean {
    public ResponseType response(RequestType input) {
        return new ResponseType("Received input" + input.getName());
    }
}
