package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.repository.JobRepository;
import com.codinginfinity.benchmark.management.repository.MeasurementRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.FileRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManegementImpl;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.QueueMessageUtils;
import com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andrew on 2016/08/30.
 */
@Configuration
@Import(ExperimentManegementImpl.class)
public class ExperimentManagentConfiguration {

    @Bean
    public UserManagement userManagement() {
        return Mockito.mock(UserManagement.class);
    }

    @Bean
    public ExperimentRepository experimentRepository() {
        return Mockito.mock(ExperimentRepository.class);
    }

    @Bean
    public AlgorithmManagement algorithmManagement() {
        return Mockito.mock(AlgorithmManagement.class);
    }

    @Bean
    public DatasetManagement datasetManagement(){
        return Mockito.mock(DatasetManagement.class);
    }

    @Bean
    public QueueMessageUtils queueMessageUtils() {
        return Mockito.mock(QueueMessageUtils.class);
    }

    @Bean
    public JobRepository jobRepository() {
        return Mockito.mock(JobRepository.class);
    }

    @Bean
    public MeasurementRepository measurementRepository() {
        return Mockito.mock(MeasurementRepository.class);
    }

    @Bean
    public FileRepository fileRepository() {
        return Mockito.mock(FileRepository.class);
    }
}
