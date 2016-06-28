package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagementImpl;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.exception.DuplicateDatasetCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.exception.NonExistentDatasetCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/06/25.
 */
@Service
public class DatasetCategoryManagementImpl extends CategoryManagementImpl<DatasetCategory, DatasetCategoryRepository> implements DatasetCategoryManagement {

    @Inject
    private DatasetCategoryRepository repository;

    @Override
    protected DatasetCategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    protected DatasetCategory newCategory() {
        return new DatasetCategory();
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
