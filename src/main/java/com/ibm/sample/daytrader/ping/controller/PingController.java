package com.ibm.sample.daytrader.ping.controller;

import com.ibm.sample.daytrader.ping.beans.DummyBean;
import com.ibm.sample.daytrader.ping.entities.DummyEntity;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import java.util.UUID;

import static org.apache.camel.model.rest.RestParamType.body;

//@RestController
//public class PingController {
//
//
//    private static PingRemoteCallService remoteCallService = new PingRemoteCallService();
//
//    @RequestMapping(value = "/ping/registerUser", method = RequestMethod.POST)
//    public ResponseEntity<Boolean> registerOneUser() throws Exception {
//        try {
//            Boolean success = remoteCallService.registerOneUser();
//            return new ResponseEntity<Boolean>(success, HttpStatus.OK);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}

@Component
public class PingController extends RouteBuilder {
        @Override
        public void configure() throws Exception {
                restConfiguration()
                                .component("servlet")
                                .bindingMode(RestBindingMode.json)
                                .dataFormatProperty("prettyPrint", "true")
                                .apiProperty("api.title", "Saga Order Creator")
                                .apiProperty("api.version", "1.0")
                // .apiContextListing(true)
                ;

                onException(Exception.class)
                                .handled(true)
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                                .setHeader(Exchange.CONTENT_TYPE, constant("text/json"))
                                .setBody().simple("${exception.message}");

                rest()
                                .consumes("application/json")
                                .produces("application/json")
                                .post("/test")
                                .responseMessage("201", "When Created")
                                .description("Receive a dummy data").type(DummyEntity.class)
                                .param().name("body").type(body).description("Payload for an dummy API call")
                                .endParam()
                                .to("direct:test");

                from("direct:test")
                                .process(exchange -> {
                                        exchange.getMessage().setHeader("id", UUID.randomUUID().toString());
                                        DummyEntity dummyEntity = exchange.getMessage().getBody(DummyEntity.class);
                                        dummyEntity.setCallId(exchange.getMessage().getHeader("id", String.class));
                                        exchange.getMessage().setBody(dummyEntity);
                                })
                                .log(LoggingLevel.TRACE, "Id: ${header.id}, message received: ${body}")
                                .bean(DummyBean.class, "printThis");
        }
}
