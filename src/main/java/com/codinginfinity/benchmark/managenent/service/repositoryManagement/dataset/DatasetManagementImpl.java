package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.exception.NonExistentDatasetException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/24.
 */
@Service
public class DatasetManagementImpl
        extends RepositoryEntityManagementImpl<DatasetCategory, Dataset,DatasetRepository, DatasetCategoryManagement>
        implements DatasetManagement{
    @Inject
    DatasetRepository datasetRepository;

    @Inject
    DatasetCategoryManagement datasetCategoryManagement;

    @Override
    protected NonExistentRepoEntityException getNonExistentRepoEntityException() {
        return new NonExistentDatasetException("Dataset does not Exist");
    }

    @Override
    protected DatasetRepository getRepository() {
        return this.datasetRepository;
    }

    @Override
    protected DatasetCategoryManagement getCategoryManagement() {
        return this.datasetCategoryManagement;
    }

    @Override
    protected Dataset newRepoEntity() {
        return new Dataset();
    }
}
