package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.thrift.messages.ThriftJobSpecificationMessageDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftMeasurementDataFormat;
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
    private ThriftMeasurementDataFormat thriftMessageDataFormat;

    @Inject
    private ThriftJobSpecificationMessageDataFormat thriftJobSpecificationMessageDataFormat;

    @Override
    public void configure() throws Exception {
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
        from("direct:jobs").marshal(thriftJobSpecificationMessageDataFormat).to("activemq:jobs");
    }
}