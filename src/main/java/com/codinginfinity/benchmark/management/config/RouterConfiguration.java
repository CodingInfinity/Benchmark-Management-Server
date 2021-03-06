package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.thrift.messages.ThriftHeartbeatMessageDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftJobSpecificationMessageDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftMeasurementDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftResultMessageDataFormat;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Defines a Spring configuration class for Apache Camel router integration.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
public class RouterConfiguration extends RouteBuilder {

    @Inject
    private CamelContext camelContext;

    @Inject
    private ThriftMeasurementDataFormat thriftMessageDataFormat;

    @Inject
    private ThriftJobSpecificationMessageDataFormat thriftJobSpecificationMessageDataFormat;

    @Inject
    private ThriftResultMessageDataFormat thriftResultMessageDataFormat;

    @Inject
    private ThriftHeartbeatMessageDataFormat thriftHeartbeatMessageDataFormat;

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Override
    public void configure() throws Exception {
        camelContext.addComponent("activemq", activeMQComponent(brokerUrl));
        from("direct:jobs").marshal(thriftJobSpecificationMessageDataFormat).to("activemq:jobs");
        from("activemq:results").unmarshal(thriftResultMessageDataFormat).to("direct:results");
        from("activemq:heartbeat").unmarshal(thriftHeartbeatMessageDataFormat).to("direct:heartbeat");
    }
}
