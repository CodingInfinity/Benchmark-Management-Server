package com.codinginfinity.benchmark.managenent.config;

import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.codinginfinity.benchmark.managenent.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {

}
