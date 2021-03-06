package com.codinginfinity.benchmark.management.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Defines a Spring configuration class for web endpoints configuration.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
@Slf4j
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    @Inject
    private BenchmarkProperties benchmarkProperties;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }

    @Bean
    @ConditionalOnProperty(name = "benchmark.cors.allowed-origins")
    public CorsFilter corsFilter() {
        log.debug("Registering CORS filter");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = benchmarkProperties.getCors();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        source.registerCorsConfiguration("/oauth/**", config);
        return new CorsFilter(source);
    }
}
