package com.codinginfinity.benchmark.management.test;

import com.codinginfinity.benchmark.management.ManagementApp;
import com.codinginfinity.benchmark.management.config.BenchmarkProperties;
import com.codinginfinity.benchmark.management.config.ThymeleafConfiguration;
import com.codinginfinity.benchmark.management.repository.elasticsearch.ArchiveRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.FileRepository;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by andrew on 2016/06/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.codinginfinity.benchmark.management.service",
        "com.codinginfinity.benchmark.management.repository"
        })
@Import({
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        MailSenderAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        ThymeleafAutoConfiguration.class,
        ThymeleafConfiguration.class})
@EnableJpaRepositories(basePackages = "com.codinginfinity.benchmark.management.repository")
@EntityScan(basePackages = {"com.codinginfinity.benchmark.management.domain"})
@EnableConfigurationProperties({ BenchmarkProperties.class })
public class ManagementTest {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApp.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FileRepository fileRepository() {
        return Mockito.mock(FileRepository.class);
    }

    @Bean
    public ArchiveRepository archiveRepository() {
        return Mockito.mock(ArchiveRepository.class);
    }

}
