package com.codinginfinity.benchmark.management.test.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagementImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andrew on 2016/06/28.
 */
@Configuration
public class AlgorithmCategorySpringTest {

    @Bean
    public AlgorithmCategoryManagement categoryManagement() {
        return new AlgorithmCategoryManagementImpl();
    }

    @Bean
    public AlgorithmCategoryRepository categoryRepository() {
        return Mockito.mock(AlgorithmCategoryRepository.class);
    }
}
