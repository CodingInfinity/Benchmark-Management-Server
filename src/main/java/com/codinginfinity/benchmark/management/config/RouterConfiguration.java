package com.codinginfinity.benchmark.management.config;

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


    @Override
    public void configure() throws Exception {
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
    }
}
