package com.codinginfinity.benchmark.management.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Defines a Spring configuration class for database configuration.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.codinginfinity.benchmark.management.repository")
@EnableElasticsearchRepositories(basePackages = "com.codinginfinity.benchmark.management.repository.elasticsearch")
@EnableTransactionManagement
@Slf4j
public class DatabaseConfiguration {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, DataSourceProperties dataSourceProperties,
                                     LiquibaseProperties liquibaseProperties) {

        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        log.debug("Configuring Liquibase");

        return liquibase;
    }
}
