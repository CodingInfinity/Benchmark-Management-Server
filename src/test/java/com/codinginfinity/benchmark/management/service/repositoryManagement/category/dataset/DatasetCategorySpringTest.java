package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.repository.DatasetCategoryRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andrew on 2016/06/28.
 */
@Configuration
public class DatasetCategorySpringTest {

    @Bean
    public DatasetCategoryManagement categoryManagement() {
        return new DatasetCategoryManagementImpl();
    }

    @Bean
    public DatasetCategoryRepository categoryRepository() {
        return Mockito.mock(DatasetCategoryRepository.class);
    }
}
