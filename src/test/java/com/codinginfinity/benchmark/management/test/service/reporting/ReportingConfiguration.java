package com.codinginfinity.benchmark.management.test.service.reporting;

import com.codinginfinity.benchmark.management.config.JacksonConfiguration;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.service.reporting.ReportingImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andrew on 2016/08/30.
 */
@Configuration
@Import({   ReportingImpl.class,
            JacksonConfiguration.class
        })
public class ReportingConfiguration {

    @Bean
    public JobRepository jobRepository() {
        return Mockito.mock(JobRepository.class);
    }
}
