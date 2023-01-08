package com.ibm.sample.daytrader.ping.routes;

import com.ibm.sample.daytrader.ping.types.RequestType;
import com.ibm.sample.daytrader.ping.types.ResponseType;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class PingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // This section is required - it tells Camel how to configure the REST service
        restConfiguration()
                // Use the 'servlet' component.
                // This tells Camel to create and use a Servlet to 'host' the RESTful API.
                // Since we're using Spring Boot, the default servlet container is Tomcat.
                .component("servlet")

                // Allow Camel to try to marshal/unmarshal between Java objects and JSON
                .bindingMode(RestBindingMode.json);

        // Now define the REST API (POST, GET, etc.)
        rest()
                .path("/api") // This makes the API available at http://host:port/$CONTEXT_ROOT/api

                .consumes("application/json")
                .produces("application/json")

                // HTTP: GET /api
                .get()
                .outType(ResponseType.class) // Setting the response type enables Camel to marshal the response to JSON
                .to("bean:getBean") // This will invoke the Spring bean 'getBean'

                // HTTP: POST /api
                .post()
                .type(RequestType.class) // Setting the request type enables Camel to unmarshal the request to a Java object
                .outType(RequestType.class) // Setting the response type enables Camel to marshal the response to JSON
                .to("bean:postBean");

    }
}
