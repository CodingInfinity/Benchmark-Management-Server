package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagementImpl;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.exception.DuplicateDatasetCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.exception.NonExistentDatasetCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * A reference implementation of the {@link DatasetCategoryManagement} interface and
 * the {@link CategoryManagementImpl} abstract class.
 *
 * Created by andrew on 2016/06/25.
 *
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.request
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.response
 * @see com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.exception
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Service
public class DatasetCategoryManagementImpl extends CategoryManagementImpl<DatasetCategory, DatasetCategoryRepository, Dataset, DatasetManagement> implements DatasetCategoryManagement {

    @Inject
    private DatasetCategoryRepository repository;

    @Inject
    private DatasetManagement datasetManagement;

    @Override
    protected DatasetCategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    protected DatasetCategory newCategory() {
        return new DatasetCategory();
    }

    @Override
    protected DatasetManagement getRepositoryManagement() {
        return this.datasetManagement;
    }

    @Override
    protected DuplicateCategoryException getDuplicateCategoryException() {
        return new DuplicateDatasetCategoryException("Duplicate dataset category");
    }

    @Override
    protected NonExistentCategoryException getNonExistentCategoryException() {
        return new NonExistentDatasetCategoryException("Dataset category doesn't exist");
    }
}
