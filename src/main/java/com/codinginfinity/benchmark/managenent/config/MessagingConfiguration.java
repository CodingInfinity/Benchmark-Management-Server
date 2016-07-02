package com.codinginfinity.benchmark.managenent.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.Objects;

/**
 * Created by andrew on 2016/06/30.
 */
@Configuration
@EnableJms
@Import({ActiveMQProperties.class})
@Slf4j
public class MessagingConfiguration {

    @Inject
    private ActiveMQProperties properties;

    @Bean
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
}