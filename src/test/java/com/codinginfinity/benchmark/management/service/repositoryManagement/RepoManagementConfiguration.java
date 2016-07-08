package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.repository.binary.ArchiveRepository;
import com.codinginfinity.benchmark.managenent.repository.binary.FileRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagementImpl;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andrew on 2016/07/06.
 */
@Configuration
public class RepoManagementConfiguration {

    @Bean
    public UserManagement userManagement() {
        return Mockito.mock(UserManagement.class);
    }

    @Bean
    public DatasetManagement datasetManagement() {
        return new DatasetManagementImpl();
    }

    @Bean
    public DatasetRepository datasetRepository() {
        return Mockito.mock(DatasetRepository.class);
    }


    @Bean
    public DatasetCategoryManagement datasetCategoryManagement() {
        return Mockito.mock(DatasetCategoryManagement.class);
    }

    @Bean
    public AlgorithmManagement algorithmManagement() {
        return new AlgorithmManagementImpl();
    }

    @Bean
    public AlgorithmRepository algorithmRepository() {
        return Mockito.mock(AlgorithmRepository.class);
    }

    @Bean
    public AlgorithmCategoryManagement algorithmCategoryManagement() {
        return Mockito.mock(AlgorithmCategoryManagement.class);
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
