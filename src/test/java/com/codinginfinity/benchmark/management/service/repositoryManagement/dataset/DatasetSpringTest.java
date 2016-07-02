package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagementImpl;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
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

    @Bean
    public UserManagement userManagement() {
        return Mockito.mock(UserManagement.class);
    }

    @Bean
    public DatasetCategoryManagement datasetCategoryManagement() {
        return Mockito.mock(DatasetCategoryManagement.class);
    }
}
