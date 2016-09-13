package com.codinginfinity.benchmark.management.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Defines a Spring configuration class for Swagger configuration.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
@EnableSwagger2
@Slf4j
@ConditionalOnProperty(prefix="benchmark.swagger", name="enabled")
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    /**
     * Swagger Springfox configuration.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket(BenchmarkProperties benchmarkProperties) {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                benchmarkProperties.getSwagger().getContactName(),
                benchmarkProperties.getSwagger().getContactUrl(),
                benchmarkProperties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                benchmarkProperties.getSwagger().getTitle(),
                benchmarkProperties.getSwagger().getDescription(),
                benchmarkProperties.getSwagger().getVersion(),
                benchmarkProperties.getSwagger().getTermsOfServiceUrl(),
                contact,
                benchmarkProperties.getSwagger().getLicense(),
                benchmarkProperties.getSwagger().getLicenseUrl());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(ZonedDateTime.class, Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}
