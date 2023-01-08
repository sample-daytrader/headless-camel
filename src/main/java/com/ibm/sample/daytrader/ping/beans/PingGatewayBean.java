package com.ibm.sample.daytrader.ping.beans;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sample.daytrader.ping.entities.AccountDataBean;
import com.ibm.sample.daytrader.ping.utils.Log;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PingGatewayBean {

    static String gatewayUrl = System.getenv("DAYTRADER_GATEWAY_SERVICE");

    protected static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper(); // create once, reuse
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ignore properties that are not
                                                                                    // declared
    }

    /**
     * Right after construction, we'll create and populate all the relevant database
     * tables in the services
     */
    @PostConstruct
    public void postConstruct() {
        Log.traceEnter(PingGatewayBean.class.getName() + "::postConstruct()");
        /*
         * TODO
         * ----
         * We need to invoke the gateway service to create all the databases prior to
         * usage.
         * I am leaving this section unimplemented for now, but it must be completed
         * when everything else works.
         */
        return;
    }

    /**
     * Right before destruction, we'll reset all the databases
     */
    @PreDestroy
    public void preDestroy() {
        /*
         * TODO
         * ----
         * This must be implemented to delete all the databases just before the bean is
         * destroyed. Not sure if this needs to be implemented. Please check...
         */
        Log.traceEnter(PingGatewayBean.class.getName() + "::preDestroy()");
        return;
    }

    public boolean pingRegisterOneUser() throws Exception {
        Log.traceEnter(PingGatewayBean.class.getName() + "::pingRegisterOneUser()");
        String pingUrl = gatewayUrl + "/accounts";
        String accountDataAsString = null;
        try {
            Log.trace("Creating a random user.");
            AccountDataBean randomUser = AccountDataBean.getRandomInstance();
            accountDataAsString = mapper.writeValueAsString(randomUser);
            Log.trace("Random user created successfully: \n" + accountDataAsString);
        } catch (Exception e) {
            Log.error("Failed to create a random user... " + e.getMessage());
            System.exit(1);
        }
        Log.traceExit("EXIT: " + PingGatewayBean.class.getName() + "::pingRegisterOneUser()");
        return false;
    }
}
