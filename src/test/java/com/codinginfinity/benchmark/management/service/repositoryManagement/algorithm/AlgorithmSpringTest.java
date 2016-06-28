package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagementImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by reinhardt on 2016/06/28.
 */
@Configuration
public class AlgorithmSpringTest {
    @Bean
    public AlgorithmManagement categoryManagement() {
        return new AlgorithmManagementImpl();
    }

    @Bean
    public AlgorithmRepository categoryRepository() {
        return Mockito.mock(AlgorithmRepository.class);
    }
}
