package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.repository.DatasetRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.RepositoryEntityManagementImpl;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.exception.NonExistentDatasetException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * A reference implementation of the {@link DatasetManagement} interface and
 * the {@link RepositoryEntityManagementImpl} abstract class.
 *
 * @see com.codinginfinity.benchmark.management.service.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.response
 *
 * Created by andrew on 2016/06/24.
 *
 * @author Andrew Broekman
 * @version 1.0.0
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
