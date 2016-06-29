package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.exception.NonExistentDatasetException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/24.
 */
@Service
public class DatasetManagementImpl
        extends RepositoryEntityManagementImpl<DatasetCategory, Dataset,DatasetRepository>
        implements DatasetManagement{
    @Inject
    DatasetRepository datasetRepository;

    @Override
    protected NonExistentRepoEntityException getNonExistentCategoryException() {
        return new NonExistentDatasetException("Dataset does not Exist");
    }

    @Override
    protected DatasetRepository getRepository() {
        return this.datasetRepository;
    }

    @Override
    protected Dataset newRepoEntity() {
        return new Dataset();
    }
}
