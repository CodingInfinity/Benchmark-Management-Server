package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Defines a Spring configuration class for Logging aspects.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
