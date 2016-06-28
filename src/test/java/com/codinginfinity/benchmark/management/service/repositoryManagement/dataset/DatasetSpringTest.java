package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagementImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by reinhardt on 2016/06/28.
 */
@Configuration
public class DatasetSpringTest {
    @Bean
    public DatasetManagement categoryManagement() {
        return new DatasetManagementImpl();
    }

    @Bean
    public DatasetRepository  categoryRepository() {
        return Mockito.mock(DatasetRepository.class);
    }
}
