package com.codinginfinity.benchmark.managenent.config;

import com.codinginfinity.benchmark.managenent.thrift.ThriftMessageDataFormat;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Created by andrew on 2016/07/03.
 */
@Configuration
public class RouterConfiguration extends RouteBuilder {

    @Inject
    private CamelContext camelContext;

    @Inject
    private ThriftMessageDataFormat thriftMessageDataFormat;


    @Override
    public void configure() throws Exception {
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
        from("direct:jobs").marshal(thriftMessageDataFormat).to("activemq:jobs");
    }
}
