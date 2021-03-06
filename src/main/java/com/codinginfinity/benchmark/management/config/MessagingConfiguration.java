package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.thrift.messages.ThriftHeartbeatMessageDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftJobSpecificationMessageDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftMeasurementDataFormat;
import com.codinginfinity.benchmark.management.thrift.messages.ThriftResultMessageDataFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.Objects;

/**
 * Defines a Spring configuration class for queue messaging configuration.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
@EnableJms
@EnableConfigurationProperties({ ActiveMQProperties.class })
@Slf4j
public class MessagingConfiguration {

    @Inject
    private ActiveMQProperties properties;

    @Bean
    @ConditionalOnProperty(name = "spring.activemq.in-memory",
        havingValue = "false")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(jmsConnectionFactory());
        factory.setSessionCacheSize(20);
        factory.setCacheConsumers(true);
        factory.setCacheProducers(true);
        factory.setReconnectOnException(true);
        return factory;
    }

    @Bean
    @Inject
    @ConditionalOnProperty(name = "spring.activemq.in-memory",
            havingValue = "false")
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        String username = properties.getUser();
        String password = properties.getPassword();
        String brokerUrl = properties.getBrokerUrl();

        log.debug("Creating JMS connection factory {}", constructConnectionURL(username, password, brokerUrl));
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    private String constructConnectionURL(String username, String password, String brokerUrl) {
        StringBuilder sb = new StringBuilder();
        if ((Objects.nonNull(username) && !username.isEmpty()) || (Objects.nonNull(password) && !password.isEmpty())) {
            sb.append(username.isEmpty() ? "" : username);
            sb.append(":");
            sb.append(password.isEmpty() ? "" : password);
            sb.append("@");
        }
        String[] tokens = brokerUrl.split("://");
        return tokens[0] + "://" + sb.toString() + tokens[1];
    }

    @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TJSONProtocol.Factory();
    }

    @Bean
    public ThriftMeasurementDataFormat thriftMessageDataFormat() {
        return new ThriftMeasurementDataFormat();
    }

    @Bean
    public ThriftJobSpecificationMessageDataFormat thriftJobSpecificationMessageDataFormat(){
        return new ThriftJobSpecificationMessageDataFormat();
    }

    @Bean
    public ThriftResultMessageDataFormat thriftResultMessageDataFormat(){
        return new ThriftResultMessageDataFormat();
    }

    @Bean
    public ThriftHeartbeatMessageDataFormat thriftHeartbeatMessageDataFormat() {
        return new ThriftHeartbeatMessageDataFormat();
    }
}
